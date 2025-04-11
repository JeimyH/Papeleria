package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Venta")
public class VentaController {
    @Autowired
    public VentaService ventaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        // Verificar si la lista está vacía
        if (ventas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(ventas, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_venta}")
    public ResponseEntity<Venta> listarPorIdVenta(@PathVariable long id_venta){
        try {
            Optional<Venta> ventaOpt = ventaService.listarPorIdVenta(id_venta);
            return ventaOpt.map(venta -> new ResponseEntity<>(venta, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta){
        try {
            Venta nuevaVenta = ventaService.guardarVenta(venta);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_venta}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable long id_venta){
        try {
            ventaService.eliminarVenta(id_venta);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_venta}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable long id_venta, @RequestBody Venta ventaActualizada){
        try {
            Venta venta = ventaService.actualizarVenta(id_venta, ventaActualizada);
            return new ResponseEntity<>(venta, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
