package com.josebaezx.pruebassr.controller;

import com.josebaezx.pruebassr.dto.AuthenticationRequest;
import com.josebaezx.pruebassr.dto.AuthenticationResponse;
import com.josebaezx.pruebassr.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {
        return null;
    }
}
