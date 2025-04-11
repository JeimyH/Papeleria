package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Empleado")
public class EmpleadoController {
    @Autowired
    public EmpleadoService empleadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        // Verificar si la lista está vacía
        if (empleados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(empleados, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_empleado}")
    public ResponseEntity<Empleado> listarPorIdEmpleado(@PathVariable long id_empleado){
        try {
            Optional<Empleado> empleadoOpt = empleadoService.listarPorIdEmpleado(id_empleado);
            return empleadoOpt.map(empleado -> new ResponseEntity<>(empleado, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado){
        try {
            Empleado nuevoEmpleado = empleadoService.guardarEmpleado(empleado);
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_empleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable long id_empleado){
        try {
            empleadoService.eliminarEmpleado(id_empleado);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_empleado}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable long id_empleado, @RequestBody Empleado empleadoActualizado){
        try {
            Empleado empleado = empleadoService.actualizarEmpleado(id_empleado, empleadoActualizado);
            return new ResponseEntity<>(empleado, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
