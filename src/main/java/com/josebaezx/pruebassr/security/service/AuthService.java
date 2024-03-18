package com.josebaezx.pruebassr.security.service;

import com.josebaezx.pruebassr.security.model.dto.AuthResponse;
import com.josebaezx.pruebassr.security.model.dto.LoginRequest;
import com.josebaezx.pruebassr.security.model.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);
}
