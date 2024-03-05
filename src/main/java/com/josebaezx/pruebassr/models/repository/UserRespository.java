package com.josebaezx.pruebassr.models.repository;

import com.josebaezx.pruebassr.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
