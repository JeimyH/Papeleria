Explicación de las consultas nativas

Consulta 1: ventas que ha realizado un empleado

@Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado", nativeQuery = true)
List<Venta> findVentasByEmpleado(@Param("idEmpleado") int idEmpleado);

Explicación:  @Query se utiliza para definir consultas personalizadas,value contiene la consulta SQL que se desea ejecutar, SELECT * FROM venta indica que se seleccionarán todas las columnas (*) de la tabla venta.
WHERE id_empleado = :idEmpleado evalua si el valor ingresado coincide con algun valor de la tabla y se devolverán las filas donde el id_empleado en la tabla venta coincida con el valor proporcionado por en :idEmpleado,
nativeQuery = true: Este parámetro indica que la consulta es una consulta SQL nativa,
findVentasByEmpleado es el nombre del método que se invocará para ejecutar la consulta,
@Param("idEmpleado") @Param se utiliza para vincular el parámetro del método (idEmpleado) con el parámetro nombrado en la consulta SQL (:idEmpleado). Esto permite que el valor del parámetro se pase a la consulta en tiempo de ejecución,
El método devuelve una lista de objetos de tipo Venta, que representa las filas devueltas por la consulta. Cada objeto Venta contendrá los datos de una fila de la tabla venta.

Consulta 2: productos que ofrece un proveedor
@Query(value = "SELECT * FROM producto WHERE id_proveedor = :idProveedor", nativeQuery = true)
List<Producto> findProductosByProveedor(@Param("idProveedor") int idProveedor);

Explicación: @Query se utiliza para definir consultas personalizadas en Spring Data JPA.
value contiene la consulta SQL que se desea ejecutar, en este caso, SELECT * FROM producto indica que se seleccionarán todas las columnas (*) de la tabla producto.
WHERE id_proveedor = :idProveedor evalúa si el valor ingresado coincide con algún valor de la tabla y se devolverán las filas donde el id_proveedor en la tabla producto coincida con el valor proporcionado por :idProveedor.
nativeQuery = true: Este parámetro indica que la consulta es una consulta SQL nativa, lo que significa que se ejecutará directamente en la base de datos sin ser procesada por JPA.
findProductosByProveedor es el nombre del método que se invocará para ejecutar la consulta. Este nombre es descriptivo y sugiere que el método recuperará los productos asociados a un proveedor específico.
@Param("idProveedor") se utiliza para vincular el parámetro del método (idProveedor) con el parámetro nombrado en la consulta SQL (:idProveedor). Esto permite que el valor del parámetro se pase a la consulta en tiempo de ejecución.
El método devuelve una lista de objetos de tipo Producto, que representa las filas devueltas por la consulta. Cada objeto Producto contendrá los datos de una fila de la tabla producto.

Consulta 3: mostrar las ventas que ha realizado un empleado a un cliente
@Query(value = "SELECT v.* FROM venta v JOIN cliente c ON v.id_cliente = c.id_cliente WHERE v.id_empleado = :idEmpleado AND c.id_cliente = :idCliente", nativeQuery = true)
List<Venta> findVentasByEmpleadoAndCliente(@Param("idEmpleado") int idEmpleado, @Param("idCliente") int idCliente);

Explicación: @Query se utiliza para definir consultas personalizadas en Spring Data JPA.
value contiene la consulta SQL que se desea ejecutar, en este caso, SELECT v.* FROM venta v indica que se seleccionarán todas las columnas (*) de la tabla venta, y se le asigna un alias v para facilitar su referencia en la consulta.
JOIN cliente c ON v.id_cliente = c.id_cliente establece una relación entre la tabla venta y la tabla cliente, uniendo las filas donde el id_cliente en la tabla venta coincide con el id_cliente en la tabla cliente. Esto permite acceder a los datos del cliente asociado a cada venta.
WHERE v.id_empleado = :idEmpleado evalúa si el valor ingresado coincide con algún valor de la tabla y se devolverán las filas donde el id_empleado en la tabla venta coincida con el valor proporcionado por :idEmpleado.
AND c.id_cliente = :idCliente añade una condición adicional que filtra los resultados para que solo se devuelvan las ventas donde el id_cliente en la tabla cliente coincida con el valor proporcionado por :idCliente.
nativeQuery = true: Este parámetro indica que la consulta es una consulta SQL nativa, lo que significa que se ejecutará directamente en la base de datos sin ser procesada por JPA.
findVentasByEmpleadoAndCliente es el nombre del método que se invocará para ejecutar la consulta. Este nombre es descriptivo y sugiere que el método recuperará las ventas realizadas por un empleado específico a un cliente específico.
@Param("idEmpleado") y @Param("idCliente") se utilizan para vincular los parámetros del método (idEmpleado y idCliente) con los parámetros nombrados en la consulta SQL (:idEmpleado y :idCliente). Esto permite que los valores de los parámetros se pasen a la consulta en tiempo de ejecución.
El método devuelve una lista de objetos de tipo Venta, que representa las filas devueltas por la consulta. Cada objeto Venta contendrá los datos de una fila de la tabla venta.

Consulta 4: mostrar los detalles de ventas que ha hecho un empleado a un cliente
@Query(value = "SELECT dv.* FROM detalle_venta dv JOIN venta v ON dv.id_venta = v.id_venta JOIN cliente c ON v.id_cliente = c.id_cliente WHERE v.id_empleado = :idEmpleado AND c.id_cliente = :idCliente", nativeQuery = true)
List<DetalleVenta> findDetallesByEmpleadoAndCliente(@Param("idEmpleado") int idEmpleado, @Param("idCliente") int idCliente);

Explicación: @Query se utiliza para definir consultas personalizadas en Spring Data JPA.
value contiene la consulta SQL que se desea ejecutar, en este caso, SELECT dv.* FROM detalle_venta dv indica que se seleccionarán todas las columnas (*) de la tabla detalle_venta, y se le asigna un alias dv para facilitar su referencia en la consulta.
JOIN venta v ON dv.id_venta = v.id_venta establece una relación entre la tabla detalle_venta y la tabla venta, uniendo las filas donde el id_venta en la tabla detalle_venta coincide con el id_venta en la tabla venta. Esto permite acceder a los datos de la venta asociada a cada detalle de venta.
JOIN cliente c ON v.id_cliente = c.id_cliente añade otra relación, uniendo la tabla venta con la tabla cliente, donde el id_cliente en la tabla venta coincide con el id_cliente en la tabla cliente. Esto permite acceder a los datos del cliente asociado a cada venta.
WHERE v.id_empleado = :idEmpleado evalúa si el valor ingresado coincide con algún valor de la tabla y se devolverán las filas donde el id_empleado en la tabla venta coincida con el valor proporcionado por :idEmpleado.
AND c.id_cliente = :idCliente añade una condición adicional que filtra los resultados para que solo se devuelvan los detalles de venta donde el id_cliente en la tabla cliente coincida con el valor proporcionado por :idCliente.
nativeQuery = true: Este parámetro indica que la consulta es una consulta SQL nativa, lo que significa que se ejecutará directamente en la base de datos sin ser procesada por JPA.
findDetallesByEmpleadoAndCliente es el nombre del método que se invocará para ejecutar la consulta. Este nombre es descriptivo y sugiere que el método recuperará los detalles de ventas realizadas por un empleado específico a un cliente específico.
@Param("idEmpleado") y @Param("idCliente") se utilizan para vincular los parámetros del método (idEmpleado y idCliente) con los parámetros nombrados en la consulta SQL (:idEmpleado y :idCliente). Esto permite que los valores de los parámetros se pasen a la consulta en tiempo de ejecución.
El método devuelve una lista de objetos de tipo DetalleVenta, que representa las filas devueltas por la consulta. Cada objeto DetalleVenta contendrá los datos de una fila de la tabla detalle_venta.

Construcción de las peticiones para las consultas en postman de la tabla Empleado

La ruta para las peticiones son las mismas para todas las tablas, solo se debe modificar el nombre de la tabla correspondiente con la primera letra en mayuscula, en la ruta despues de /api/

GET General

Ruta de la petición
http://localhost:8080/api/Empleado/listar

GET Individual

Ruta de la petición
http://localhost:8080/api/Empleado/buscar/1

POST

Ruta de la petición
http://localhost:8080/api/Empleado/guardar

Body
{
"nombre": "Andres",
"cargo": "Vendedor",
"telefono": "3118165633"
}

PUT

Ruta de la petición
http://localhost:8080/api/Empleado/actualizar/1

Body
{
"nombre": "Carlos",
"cargo": "Vendedor",
"telefono": "3118165633"
}

DELETE

Ruta de la petición
http://localhost:8080/api/Empleado/eliminar/6

