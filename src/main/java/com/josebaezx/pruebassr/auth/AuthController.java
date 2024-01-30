package com.josebaezx.pruebassr.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginGet(){
        return "Bienvenido al LOGIN";
    }
    @PostMapping("/login")
    public String login(){
        return "Datos enviados para logueo";
    }

    @PostMapping("/registro")
    public String registro(){
        return "Bienvenido al REGISTRO";
    }
}
