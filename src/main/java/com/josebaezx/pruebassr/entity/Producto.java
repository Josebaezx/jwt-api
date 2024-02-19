package com.josebaezx.pruebassr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Null
    private Long id;
    @NotBlank
    private String name;
    @DecimalMin(value="0.01")
    private BigDecimal price;
}
