package com.example.Papeleria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Detalle_Venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detalle;

    //@Column(name = "ID_Venta", nullable = false, length = 20)
    //private int id_venta;

    //@Column(name = "ID_Producto", nullable = false, length = 20)
    //private int id_producto;

    @Column(name = "Cantidad", nullable = false, length = 50)
    private int cantidad;

    @Column(name = "Precio Unitario", nullable = false, length = 50)
    private double precio_unitario;

    @ManyToOne
    @JoinColumn(name="id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name="id_producto", nullable = false)
    private Producto producto;
}
