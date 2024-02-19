package com.josebaezx.pruebassr.controller;

import com.josebaezx.pruebassr.entity.Producto;
import com.josebaezx.pruebassr.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    
    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducts() {
        List<Producto> productos = productRepository.findAll();

        if(productos != null && productos.isEmpty()) {
            return ResponseEntity.ok(productos);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/products")
    public ResponseEntity<Producto> getProductsByName(String name) {
        Producto producto = productRepository.findByName(name);

        return ResponseEntity.ok(producto);
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable Long id) {
        Producto producto = productRepository.findById(id).get();

        return ResponseEntity.ok(producto);
    }
    
    @PostMapping("/products")
    public ResponseEntity<Producto> saveProduct(@RequestBody @Valid Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productRepository.save(producto)
        );
    }
    
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    
    
    
}
