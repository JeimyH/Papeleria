package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Model.Proveedor;
import com.example.Papeleria.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    public ProveedorRepository proveedorRepository;

    public List<Proveedor> listarProveedores(){
        // Validacion para intentar obtener la lista de clientes
        try {
            List<Proveedor> proveedores = proveedorRepository.findAll();
            // Validar que la lista no sea nula
            if (proveedores == null) {
                throw new IllegalStateException("No se encontraron proveedores.");
            }
            return proveedores;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar proveedores: " + e.getMessage(), e);
        }
    }

    public Optional<Proveedor> listarPorIdProveedor(long id_proveedor){
        try {
            Optional<Proveedor> proveedor = proveedorRepository.findById(id_proveedor);
            if (proveedor.isPresent()) {
                return proveedor;
            } else {
                throw new IllegalStateException("No se encontraron proveedores.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el proveedor " + id_proveedor +": "+ e.getMessage(), e);
        }
    }

    public Proveedor guardarProveedor(Proveedor proveedor){
        try{
            if(proveedor==null){
                throw new IllegalArgumentException("El cliente no puede ser nulo");

            }else{
                if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del proveedor es obligatorio.");
                }else if(proveedor.getDireccion()== null || proveedor.getDireccion().isEmpty()){
                    throw new IllegalArgumentException("La direccion del proveedor es obligatorio.");
                }else if(proveedor.getTelefono()==null || proveedor.getTelefono().isEmpty()) {
                    throw new IllegalArgumentException("El telefono del proveedor es obligatorio.");
                }else if(proveedor.getCorreo() == null || proveedor.getCorreo().isEmpty()){
                    throw new IllegalArgumentException("El correo del proveedor es obligatorio.");
                }
                return  proveedorRepository.save(proveedor);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el proveedor" + e.getMessage(), e);
        }
    }

    public void eliminarProveedor(long id_proveedor){
        try {
            if (id_proveedor<=0) {
                throw new IllegalArgumentException("El ID del proveedor debe ser un número positivo.");
            }
            if (!proveedorRepository.existsById(id_proveedor)) {
                throw new NoSuchElementException("No se encontró un proveedor con el ID: " + id_proveedor);
            }
            proveedorRepository.deleteById(id_proveedor);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el proveedor "+ id_proveedor +": "+ e.getMessage(), e);
        }
    }

    public Proveedor actualizarProveedor(long id_proveedor, Proveedor proveedorActualizado){
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id_proveedor);
        if(proveedorOpt.isPresent()){
            Proveedor proveedorExistente = proveedorOpt.get();
            proveedorExistente.setNombre(proveedorActualizado.getNombre());
            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
            proveedorExistente.setCorreo(proveedorActualizado.getCorreo());
            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
            return proveedorRepository.save(proveedorExistente);
        } else {
            return null;
        }
    }

}
