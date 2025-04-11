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
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_proveedor;

    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "Correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "Direccion", nullable = false, length = 50)
    private String direccion;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;

}
