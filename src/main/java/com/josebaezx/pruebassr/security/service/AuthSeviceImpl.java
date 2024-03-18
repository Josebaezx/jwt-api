package com.josebaezx.pruebassr.security.service;

import com.josebaezx.pruebassr.security.model.dto.AuthResponse;
import com.josebaezx.pruebassr.security.model.dto.LoginRequest;
import com.josebaezx.pruebassr.security.model.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthSeviceImpl implements AuthService{
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        return null;
    }
}
