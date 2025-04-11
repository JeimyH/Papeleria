package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Producto")
public class ProductoController {
    @Autowired
    public ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        // Verificar si la lista está vacía
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(productos, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_producto}")
    public ResponseEntity<Producto> listarPorIdProducto(@PathVariable long id_producto){
        try {
            Optional<Producto> productoOpt = productoService.listarPorIdProducto(id_producto);
            return productoOpt.map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
        try {
            Producto nuevoProducto = productoService.guardarProducto(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable long id_producto){
        try {
            productoService.eliminarProducto(id_producto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_producto}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable long id_producto, @RequestBody Producto productoActualizado){
        try {
            Producto producto = productoService.actualizarProducto(id_producto, productoActualizado);
            return new ResponseEntity<>(producto, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
