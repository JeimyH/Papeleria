package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Detalle")
public class DetalleVentaController {
    @Autowired
    public DetalleVentaService detalleVentaService;

    @GetMapping("/listar")
    public ResponseEntity<List<DetalleVenta>> listarDetallesVenta() {
        List<DetalleVenta> detalleVentas = detalleVentaService.listarDetallesVentas();
        // Verificar si la lista está vacía
        if (detalleVentas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(detalleVentas, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_detalle}")
    public ResponseEntity<DetalleVenta> listarPorIdDetalleVenta(@PathVariable long id_detalle){
        try {
            Optional<DetalleVenta> detalleVentaOpt = detalleVentaService.listarPorIdDetalleVenta(id_detalle);
            return detalleVentaOpt.map(detalleVenta -> new ResponseEntity<>(detalleVenta, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<DetalleVenta> guardarDetalleVenta(@RequestBody DetalleVenta detalleVenta){
        try {
            DetalleVenta nuevoDetalleVenta = detalleVentaService.guardarDetalleVenta(detalleVenta);
            return new ResponseEntity<>(nuevoDetalleVenta, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_detalle}")
    public ResponseEntity<Void> eliminarDetalleVenta(@PathVariable long id_detalle){
        try {
            detalleVentaService.eliminarDetalleVenta(id_detalle);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_detalle}")
    public ResponseEntity<DetalleVenta> actualizarDetalleVenta(@PathVariable long id_detalle, @RequestBody DetalleVenta detalleVentaActualizado){
        try {
            DetalleVenta detalleVenta = detalleVentaService.actualizarDetalleVenta(id_detalle, detalleVentaActualizado);
            return new ResponseEntity<>(detalleVenta, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @GetMapping("/empleado/{id_empleado}/cliente/{id_cliente}")
    public List<DetalleVenta> getDetallesByEmpleadoAndCliente(@PathVariable int id_empleado, @PathVariable int id_cliente) {
        return detalleVentaService.getDetallesByEmpleadoAndCliente(id_empleado, id_cliente);
    }

}
