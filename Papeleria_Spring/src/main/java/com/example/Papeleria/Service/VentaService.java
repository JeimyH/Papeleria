package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Cliente;
import com.example.Papeleria.Model.Venta;
import com.example.Papeleria.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    public VentaRepository ventaRepository;

    public List<Venta> listarVentas(){
        // Validacion para intentar obtener la lista de clientes
        try {
            List<Venta> ventas = ventaRepository.findAll();
            // Validar que la lista no sea nula
            if (ventas == null) {
                throw new IllegalStateException("No se encontraron ventas.");
            }
            return ventas;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar ventas: " + e.getMessage(), e);
        }
    }

    public Optional<Venta> listarPorIdVenta(long id_venta){
        try {
            Optional<Venta> venta = ventaRepository.findById(id_venta);
            if (venta.isPresent()) {
                return venta;
            } else {
                throw new IllegalStateException("No se encontraron ventas.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar la venta " + id_venta +": "+ e.getMessage(), e);
        }
    }

    public Venta guardarVenta(Venta venta){
        try{
            if(venta==null){
                throw new IllegalArgumentException("La venta no puede ser nulo");

            }else{
                if (venta.getFecha() == null) {
                    throw new IllegalArgumentException("La fecha de la venta es obligatorio.");
                }
                return  ventaRepository.save(venta);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar la venta" + e.getMessage(), e);
        }
    }

    public void eliminarVenta(long id_venta){
        try {
            if (id_venta<=0) {
                throw new IllegalArgumentException("El ID de la venta debe ser un número positivo.");
            }
            if (!ventaRepository.existsById(id_venta)) {
                throw new NoSuchElementException("No se encontró una venta con el ID: " + id_venta);
            }
            ventaRepository.deleteById(id_venta);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar la venta "+ id_venta +": "+ e.getMessage(), e);
        }
    }

    public Venta actualizarVenta(long id_venta, Venta ventaActualizado){
        Optional<Venta> ventaOpt = ventaRepository.findById(id_venta);
        if(ventaOpt.isPresent()){
            Venta ventaExistente = ventaOpt.get();
            ventaExistente.setFecha(ventaActualizado.getFecha());
            return ventaRepository.save(ventaExistente);
        } else {
            return null;
        }
    }

}
