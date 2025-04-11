package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado", nativeQuery = true)
    List<Venta> findVentasByEmpleado(@Param("idEmpleado") int idEmpleado);

    @Query(value = "SELECT v.* FROM venta v JOIN cliente c ON v.id_cliente = c.id_cliente WHERE v.id_empleado = :idEmpleado AND c.id_cliente = :idCliente", nativeQuery = true)
    List<Venta> findVentasByEmpleadoAndCliente(@Param("idEmpleado") int idEmpleado, @Param("idCliente") int idCliente);
}
