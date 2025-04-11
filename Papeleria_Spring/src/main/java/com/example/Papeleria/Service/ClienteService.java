package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    public ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){
        // Validacion para intentar obtener la lista de clientes
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            // Validar que la lista no sea nula
            if (clientes == null) {
                throw new IllegalStateException("No se encontraron clientes.");
            }
            return clientes;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar clientes: " + e.getMessage(), e);
        }

        //return clienteRepository.findAll();
    }

    public Optional<Cliente> listarPorIdCliente(long id_cliente){
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
            if (cliente.isPresent()) {
                return cliente;
            } else {
                throw new IllegalStateException("No se encontraron clientes.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el cliente " + id_cliente +": "+ e.getMessage(), e);
        }
    }

    public Cliente guardarCliente(Cliente cliente){
        try{
            if(cliente==null){
                throw new IllegalArgumentException("El cliente no puede ser nulo");

            }else{
                if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
                }else if(cliente.getCedula()== null || cliente.getCedula().isEmpty()){
                        throw new IllegalArgumentException("La cedula del cliente es obligatorio.");
                }else if(cliente.getTelefono()==null || cliente.getTelefono().isEmpty()) {
                    throw new IllegalArgumentException("El telefono del cliente es obligatorio.");
                }else if(cliente.getCorreo() == null || cliente.getCorreo().isEmpty()){
                    throw new IllegalArgumentException("El correo del cliente es obligatorio.");
                }
                return  clienteRepository.save(cliente);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el cliente" + e.getMessage(), e);
        }
    }

    public void eliminarCliente(long id_cliente){
        try {
            if (id_cliente<=0) {
                throw new IllegalArgumentException("El ID del cliente debe ser un número positivo.");
            }
            if (!clienteRepository.existsById(id_cliente)) {
                throw new NoSuchElementException("No se encontró un cliente con el ID: " + id_cliente);
            }
            clienteRepository.deleteById(id_cliente);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el cliente "+ id_cliente +": "+ e.getMessage(), e);
        }
    }

    public Cliente actualizarCliente(long id_cliente, Cliente clienteActualizado){
        Optional<Cliente> clienteOpt = clienteRepository.findById(id_cliente);
        if(clienteOpt.isPresent()){
            Cliente clienteExistente = clienteOpt.get();
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setCedula(clienteActualizado.getCedula());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            clienteExistente.setCorreo(clienteActualizado.getCorreo());
            return clienteRepository.save(clienteExistente);
        }else{
            return null;
        }
    }

}
