package com.example.febcact3_t4.controller;

import com.example.febcact3_t4.model.Producto;
import com.example.febcact3_t4.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // READ: Obtener todos los productos
    @GetMapping
    public List<Producto> listar() {
        return productoService.listarProductos();
    }

    // CREATE: Guardar un nuevo producto
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    // UPDATE: Actualizar un producto existente
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        
        producto.setNombre(productoDetalles.getNombre());
        producto.setPrecio(productoDetalles.getPrecio());
        producto.setCategoria(productoDetalles.getCategoria());
        
        return productoService.guardarProducto(producto);
    }

    // DELETE: Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "Producto eliminado con éxito (ID: " + id + ")";
    }
}
