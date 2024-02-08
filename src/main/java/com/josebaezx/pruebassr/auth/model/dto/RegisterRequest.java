package com.josebaezx.pruebassr.auth.model.dto;

import com.josebaezx.pruebassr.auth.model.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String firstname;
    private String Lastname;
    private String email;
    private String salt;
    private String hash;
    @Enumerated(EnumType.STRING)
    private Role role;
}
