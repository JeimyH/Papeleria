package com.example.Papeleria.Repository;

import com.example.Papeleria.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "SELECT * FROM Producto WHERE id_proveedor = :idProveedor", nativeQuery = true)
    List<Producto> findProductosByProveedor(@Param("idProveedor") long idProveedor);
}
