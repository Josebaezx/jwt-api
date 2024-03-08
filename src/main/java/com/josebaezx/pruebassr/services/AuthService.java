package com.josebaezx.pruebassr.services;

import com.josebaezx.pruebassr.models.dto.LoginDto;
import com.josebaezx.pruebassr.models.dto.ResponseDto;
import com.josebaezx.pruebassr.models.entity.UserEntity;

import java.util.HashMap;

public interface AuthService {
    public HashMap<String,String> login(LoginDto loginDto) throws Exception;
    public ResponseDto register(UserEntity user) throws Exception;
}
