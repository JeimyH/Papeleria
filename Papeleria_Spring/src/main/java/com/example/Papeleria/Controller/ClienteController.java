package com.example.Papeleria.Controller;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Cliente")
public class ClienteController {
    @Autowired
    public ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        // Verificar si la lista está vacía
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_cliente}")
    public ResponseEntity<Cliente> listarPorIdCliente(@PathVariable long id_cliente){
        try {
            Optional<Cliente> clienteOpt = clienteService.listarPorIdCliente(id_cliente);
            return clienteOpt.map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente){
        try {
            Cliente nuevoCliente = clienteService.guardarCliente(cliente);
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable long id_cliente){
        try {
            clienteService.eliminarCliente(id_cliente);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_cliente}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable long id_cliente, @RequestBody Cliente clienteActualizado){
        try {
            Cliente cliente = clienteService.actualizarCliente(id_cliente, clienteActualizado);
            return new ResponseEntity<>(cliente, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

}
