package com.josebaezx.pruebassr.controller;

import com.josebaezx.pruebassr.dto.AuthenticationRequest;
import com.josebaezx.pruebassr.dto.AuthenticationResponse;
import com.josebaezx.pruebassr.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {
        return null;
    }

    @GetMapping("/bienvenida")
    public String bievenida() {
        return "MarvelApi By Josebaezx";
    }
}
