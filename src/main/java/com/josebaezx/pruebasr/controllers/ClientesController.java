package com.josebaezx.pruebasr.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/demo")
public class ClientesController {

    @GetMapping()
    public String muestra(){
        return "HOLA MUNDO";
    }
}
