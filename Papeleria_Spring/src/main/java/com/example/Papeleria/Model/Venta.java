package com.example.Papeleria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_venta;

    @Column(name = "Fecha_Venta", nullable = false)
    private LocalDate fecha;

    //@Column(name = "ID_CLiente", nullable = false)
    //private int id_cliente;

    //@Column(name = "ID_Empleado", nullable = false)
    //private int id_empleado;

    @ManyToOne
    @JoinColumn(name="id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="id_empleado", nullable = false)
    private Empleado empleado;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleVenta> DetallesVentas;
}

