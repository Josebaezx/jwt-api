package com.josebaezx.pruebassr.repository;

import com.josebaezx.pruebassr.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Producto, Long> {
    Producto findByName(String name);
}
