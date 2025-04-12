package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    @Query(value = "SELECT dv.* FROM Detalle_Venta dv JOIN Ventas v ON dv.id_venta = v.id_venta JOIN Cliente c ON v.id_cliente = c.id_cliente WHERE v.id_empleado = :idEmpleado AND c.id_cliente = :idCliente", nativeQuery = true)
    List<DetalleVenta> findDetallesByEmpleadoAndCliente(@Param("idEmpleado") long idEmpleado, @Param("idCliente") long idCliente);
}
