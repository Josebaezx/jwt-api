package com.josebaezx.pruebassr.security.controller;

import com.josebaezx.pruebassr.security.model.dto.AuthResponse;
import com.josebaezx.pruebassr.security.model.dto.LoginRequest;
import com.josebaezx.pruebassr.security.model.dto.RegisterRequest;
import com.josebaezx.pruebassr.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping(value ="/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping(value ="/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
