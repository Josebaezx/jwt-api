package com.josebaezx.pruebassr.security.service;

import com.josebaezx.pruebassr.security.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String getToken(User user);
    String createToken(Map<String, Object> extraClaim, User user);

    String getEmailFromToken(String token);

    boolean isTokenValid(String token, User userDetails);
}
