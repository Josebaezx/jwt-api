package com.josebaezx.pruebassr.auth.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
    private String email;
    private String salt;
    private String hash;
}
