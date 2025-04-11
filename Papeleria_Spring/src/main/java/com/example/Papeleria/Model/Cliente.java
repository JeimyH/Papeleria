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
@Table(name = "Cientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cliente;

    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Cedula", nullable = false, length = 20)
    private String cedula;

    @Column(name = "Telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "Correo", nullable = false, length = 50)
    private String correo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

}
