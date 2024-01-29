package com.josebaezx.pruebassr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;

@RestController
@RequestMapping ("/clientes")
public class ClientesController {
    @Autowired
    private RestTemplate restTemplate;

    private String url ="http://localhost:3000/api/clientes";

    //Prueba obtener datos de api NodeJS ok
    @GetMapping()
    public ResponseEntity<Object> muestra(){
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Object.class);

    }
}
