package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Empleado;
import com.example.Papeleria.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    public EmpleadoRepository empleadoRepository;

    public List<Empleado> listarEmpleados(){
        // Validacion para intentar obtener la lista de clientes
        try {
            List<Empleado> empleados = empleadoRepository.findAll();
            // Validar que la lista no sea nula
            if (empleados == null) {
                throw new IllegalStateException("No se encontraron empleados.");
            }
            return empleados;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar empleados: " + e.getMessage(), e);
        }
    }

    public Optional<Empleado> listarPorIdEmpleado(long id_empleado){
        try {
            Optional<Empleado> empleado = empleadoRepository.findById(id_empleado);
            if (empleado.isPresent()) {
                return empleado;
            } else {
                throw new IllegalStateException("No se encontraron empleados.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el empleado " + id_empleado +": "+ e.getMessage(), e);
        }

    }

    public Empleado guardarEmpleado(Empleado empleado){
        try{
            if(empleado==null){
                throw new IllegalArgumentException("El cliente no puede ser nulo");

            }else{
                if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del empleado es obligatorio.");
                }else if(empleado.getCargo()== null || empleado.getCargo().isEmpty()){
                    throw new IllegalArgumentException("El cargo del empleado es obligatorio.");
                }else if(empleado.getTelefono()==null || empleado.getTelefono().isEmpty()) {
                    throw new IllegalArgumentException("El telefono del empleado es obligatorio.");
                }
                return  empleadoRepository.save(empleado);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el empleado" + e.getMessage(), e);
        }
    }

    public void eliminarEmpleado(long id_empleado){
        try {
            if (id_empleado<=0) {
                throw new IllegalArgumentException("El ID del empleado debe ser un número positivo.");
            }
            if (!empleadoRepository.existsById(id_empleado)) {
                throw new NoSuchElementException("No se encontró un cliente con el ID: " + id_empleado);
            }
            empleadoRepository.deleteById(id_empleado);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el cliente "+ id_empleado +": "+ e.getMessage(), e);
        }
    }

    public Empleado actualizarEmpleado(long id_empleado, Empleado empleadoActualizado){
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id_empleado);
        if(empleadoOpt.isPresent()) {
            Empleado empleadoExistente = empleadoOpt.get();
            empleadoExistente.setNombre(empleadoActualizado.getNombre());
            empleadoExistente.setCargo(empleadoActualizado.getCargo());
            empleadoExistente.setTelefono(empleadoActualizado.getTelefono());
            return empleadoRepository.save(empleadoExistente);
        } else {
          return null;
        }
    }

}
