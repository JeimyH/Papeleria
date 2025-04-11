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
@Table(name = "Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_empleado;

    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Cargo", nullable = false, length = 30)
    private String cargo;

    @Column(name = "Telefono", nullable = false, length = 10)
    private String telefono;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

}
