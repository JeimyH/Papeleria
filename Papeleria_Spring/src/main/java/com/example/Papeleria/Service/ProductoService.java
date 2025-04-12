package com.example.Papeleria.Service;

import com.example.Papeleria.Model.Producto;
import com.example.Papeleria.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    public ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        // Validacion para intentar obtener la lista de clientes
        try {
            List<Producto> productos = productoRepository.findAll();
            // Validar que la lista no sea nula
            if (productos == null) {
                throw new IllegalStateException("No se encontraron productos.");
            }
            return productos;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar los productos: " + e.getMessage(), e);
        }
    }

    public Optional<Producto> listarPorIdProducto(long id_producto){
        try {
            Optional<Producto> producto = productoRepository.findById(id_producto);
            if (producto.isPresent()) {
                return producto;
            } else {
                throw new IllegalStateException("No se encontraron productos.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el producto " + id_producto +": "+ e.getMessage(), e);
        }
    }

    public Producto guardarProducto(Producto producto){
        try{
            if(producto==null){
                throw new IllegalArgumentException("El producto no puede ser nulo");

            }else{
                if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del producto es obligatorio.");
                }else if(producto.getDescripcion()== null || producto.getDescripcion().isEmpty()){
                    throw new IllegalArgumentException("La descripcion del producto es obligatorio.");
                }else if(producto.getPrecio()==0) {
                    throw new IllegalArgumentException("El precio del producto es obligatorio.");
                }else if(producto.getStock() == 0){
                    throw new IllegalArgumentException("El stock del producto es obligatorio.");
                }
                return  productoRepository.save(producto);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el producto" + e.getMessage(), e);
        }
    }

    public void eliminarProducto(long id_producto){
        try {
            if (id_producto<=0) {
                throw new IllegalArgumentException("El ID del producto debe ser un número positivo.");
            }
            if (!productoRepository.existsById(id_producto)) {
                throw new NoSuchElementException("No se encontró un cliente con el ID: " + id_producto);
            }
            productoRepository.deleteById(id_producto);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el cliente "+ id_producto +": "+ e.getMessage(), e);
        }
    }

    public Producto actualizarProducto(long id_producto, Producto productoActualizado){
        Optional<Producto> productoOpt = productoRepository.findById(id_producto);
        if(productoOpt.isPresent()){
            Producto productoExistente = productoOpt.get();
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setDescripcion(productoActualizado.getDescripcion());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setStock(productoActualizado.getStock());
            return productoRepository.save(productoExistente);
        } else {
            return null;
        }
    }

    // Consulta nativa: Productos por proveedor
    public List<Producto> obtenerProductosPorProveedor(Long idProveedor) {
        return productoRepository.findProductosByProveedor(idProveedor);
    }

}
