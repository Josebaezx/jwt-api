package com.josebaezx.pruebassr.controllers;

import com.josebaezx.pruebassr.jwt.service.JwtUtiliyService;
import com.josebaezx.pruebassr.models.dto.LoginDto;
import com.josebaezx.pruebassr.models.dto.ResponseDto;
import com.josebaezx.pruebassr.models.entity.UserEntity;
import com.josebaezx.pruebassr.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody UserEntity user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String> > login(@RequestBody LoginDto loginDto) throws Exception {
        HashMap<String, String> login = authService.login(loginDto);
        if(login.containsKey("jwt")){
            return new ResponseEntity<>(login, HttpStatus.OK);
        }
        return new ResponseEntity<>(login, HttpStatus.UNAUTHORIZED);
    }
}
