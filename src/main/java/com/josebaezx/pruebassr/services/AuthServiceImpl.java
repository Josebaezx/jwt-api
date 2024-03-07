package com.josebaezx.pruebassr.services;

import com.josebaezx.pruebassr.jwt.service.JwtUtiliyService;
import com.josebaezx.pruebassr.models.repository.UserRespository;
import com.josebaezx.pruebassr.utils.validations.UserValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRespository userRespository;
    private final JwtUtiliyService jwtUtiliyService;
    private final UserValidations userValidations;
}
