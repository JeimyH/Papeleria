package com.example.Papeleria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_producto;

    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Descripcion", nullable = false, length = 100)
    private String Descripcion;

    @Column(name = "Precio", nullable = false, length = 50)
    private double precio;

    @Column(name = "Stock", nullable = false, length = 50)
    private int stock;

    //@Column(name = "ID_Proveedor", nullable = false, length = 20)
    //private int id_proveedor;

    @ManyToOne
    @JoinColumn(name="id_proveedor", nullable = false)
    private Proveedor proveedor;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleVenta> DetallesVentas;

}
