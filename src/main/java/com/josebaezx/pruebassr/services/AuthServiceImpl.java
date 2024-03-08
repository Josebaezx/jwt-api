package com.josebaezx.pruebassr.services;

import com.josebaezx.pruebassr.jwt.service.JwtUtiliyService;
import com.josebaezx.pruebassr.models.dto.LoginDto;
import com.josebaezx.pruebassr.models.dto.ResponseDto;
import com.josebaezx.pruebassr.models.entity.UserEntity;
import com.josebaezx.pruebassr.models.repository.UserRespository;
import com.josebaezx.pruebassr.utils.validations.UserValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRespository userRespository;
    private final JwtUtiliyService jwtUtiliyService;
    private final UserValidations userValidations;
    @Override
    public HashMap<String,String> login(LoginDto loginDto) throws Exception {
        try {
            HashMap<String,String> jwtToken = new HashMap<String,String>();
            Optional<UserEntity> user = userRespository.findByEmail(loginDto.getEmail());

            if (user.isEmpty()) {
                jwtToken.put("error","User not registered");
                return jwtToken;
            }

            if (verifyPassword(loginDto.getPassword(),user.get().getPassword())){
                jwtToken.put("jwt",jwtUtiliyService.generateJWT(user.get().getId()));
            }else{
                jwtToken.put("error","Invalid password");
            }
            return jwtToken;

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public ResponseDto register(UserEntity user) throws Exception {
        try {
            ResponseDto responseDto = userValidations.userValid(user);
            if (responseDto.getNumOfErros() > 0) {
                return responseDto;
            }
            List<UserEntity> allUsers = userRespository.findAll();

            for(UserEntity u : allUsers){
                if(u.getEmail().equals(user.getEmail())){
                    responseDto.setNumOfErros(responseDto.getNumOfErros() + 1);
                    responseDto.setMessage("User already registered");
                    return responseDto;
                }
            }
            BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRespository.save(user);
            responseDto.setMessage("User created successfully!");

            return responseDto;

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private boolean verifyPassword(String password, String password1) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, password1);
    }
}
