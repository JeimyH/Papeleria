package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Proveedor")
public class ProveedorController {
    @Autowired
    public ProveedorService proveedorService;

    @GetMapping("/listar")
    public ResponseEntity<List<Proveedor>> listarProveedores() {
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        // Verificar si la lista está vacía
        if (proveedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(proveedores, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_proveedor}")
    public ResponseEntity<Proveedor> listarPorIdProveedor(@PathVariable long id_proveedor){
        try {
            Optional<Proveedor> proveedorOpt = proveedorService.listarPorIdProveedor(id_proveedor);
            return proveedorOpt.map(proveedor -> new ResponseEntity<>(proveedor, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor){
        try {
            Proveedor nuevoProveedor = proveedorService.guardarProveedor(proveedor);
            return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_proveedor}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable long id_proveedor){
        try {
            proveedorService.eliminarProveedor(id_proveedor);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_prveedor}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable long id_proveedor, @RequestBody Proveedor proveedorActualizado){
        try {
            Proveedor proveedor = proveedorService.actualizarProveedor(id_proveedor, proveedorActualizado);
            return new ResponseEntity<>(proveedor, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
