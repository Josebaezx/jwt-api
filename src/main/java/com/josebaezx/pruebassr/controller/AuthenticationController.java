package com.josebaezx.pruebassr.controller;

import com.josebaezx.pruebassr.dto.AuthenticationRequest;
import com.josebaezx.pruebassr.dto.AuthenticationResponse;
import com.josebaezx.pruebassr.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationservice;

    @PreAuthorize("permitAll")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {
        AuthenticationResponse jwtDto = this.authenticationservice.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }
    @PreAuthorize("permitAll")
    @GetMapping("/bienvenida")
    public String bievenida() {
        return "MarvelApi By Josebaezx";
    }
}
