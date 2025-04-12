package com.example.Papeleria.Service;

import com.example.Papeleria.Model.DetalleVenta;
import com.example.Papeleria.Repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DetalleVentaService {
    @Autowired
    public DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> listarDetallesVentas(){
        // Validacion para intentar obtener la lista de clientes
        try {
            List<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();
            // Validar que la lista no sea nula
            if (detalleVentas == null) {
                throw new IllegalStateException("No se encontraron Detalles de Venta.");
            }
            return detalleVentas;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar los detalles de venta: " + e.getMessage(), e);
        }
    }

    public Optional<DetalleVenta> listarPorIdDetalleVenta(long id_detalle){
        try {
            Optional<DetalleVenta> detalleVenta = detalleVentaRepository.findById(id_detalle);
            if (detalleVenta.isPresent()) {
                return detalleVenta;
            } else {
                throw new IllegalStateException("No se encontraron clientes.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el cliente " + id_detalle +": "+ e.getMessage(), e);
        }
    }

    public DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta){
        try{
            if(detalleVenta==null){
                throw new IllegalArgumentException("El detalle de venta no puede ser nulo");

            }else{
                if (detalleVenta.getCantidad() == 0 ) {
                    throw new IllegalArgumentException("La cantidad es obligatorio.");
                }else if(detalleVenta.getPrecio_unitario()==0 ){
                    throw new IllegalArgumentException("El precio es obligatorio.");
                }
                return  detalleVentaRepository.save(detalleVenta);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el cliente" + e.getMessage(), e);
        }
    }

    public void eliminarDetalleVenta(long id_detalle){
        try {
            if (id_detalle<=0) {
                throw new IllegalArgumentException("El ID del detalle de venta debe ser un número positivo.");
            }
            if (!detalleVentaRepository.existsById(id_detalle)) {
                throw new NoSuchElementException("No se encontró un detalle de venta con el ID: " + id_detalle);
            }
            detalleVentaRepository.deleteById(id_detalle);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el detalle de venta "+ id_detalle +": "+ e.getMessage(), e);
        }
    }

    public DetalleVenta actualizarDetalleVenta(long id_detalle, DetalleVenta detalleVentaActualizado){
        Optional<DetalleVenta> detalleVentaOpt = detalleVentaRepository.findById(id_detalle);
        if(detalleVentaOpt.isPresent()){
            DetalleVenta detalleVentaExistente = detalleVentaOpt.get();
            detalleVentaExistente.setCantidad(detalleVentaActualizado.getCantidad());
            detalleVentaExistente.setPrecio_unitario(detalleVentaActualizado.getPrecio_unitario());
            return detalleVentaRepository.save(detalleVentaActualizado);
        }else{
            return null;
        }
    }

    // Consulta nativa: Detalles de venta por empleado y cliente
    public List<DetalleVenta> obtenerDetallesVentaPorEmpleadoYCliente(Long idEmpleado, Long idCliente) {
        return detalleVentaRepository.findDetallesByEmpleadoAndCliente(idEmpleado, idCliente);
    }

}
