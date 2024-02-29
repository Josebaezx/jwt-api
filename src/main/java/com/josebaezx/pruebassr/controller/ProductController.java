package com.josebaezx.pruebassr.controller;

import com.josebaezx.pruebassr.entity.Producto;
import com.josebaezx.pruebassr.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    
    private final ProductRepository productRepository;
    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducts() {
        List<Producto> productos = productRepository.findAll();

        if(productos != null && !productos.isEmpty()) {
            return ResponseEntity.ok(productos);
        }
        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
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
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e, HttpServletRequest request) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put("message", e.getLocalizedMessage());
        apiError.put("timestamp", new Date().toString());
        apiError.put("url", request.getRequestURL().toString());
        apiError.put("method", request.getMethod());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if( e instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
        }


        return ResponseEntity.status(status).body(apiError);
    }
    
}
