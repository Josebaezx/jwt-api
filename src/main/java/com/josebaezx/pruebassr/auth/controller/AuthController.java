package com.josebaezx.pruebassr.auth.controller;

import com.josebaezx.pruebassr.auth.model.dto.AuthResponse;
import com.josebaezx.pruebassr.auth.model.dto.LoginRequest;
import com.josebaezx.pruebassr.auth.model.dto.RegisterRequest;
import com.josebaezx.pruebassr.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginGet(){
        return "Bienvenido al LOGIN";
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
