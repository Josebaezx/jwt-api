package com.josebaezx.pruebassr.security.service;

import com.josebaezx.pruebassr.security.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{
    @Value("${jwt.token.expires}")
    private int MINUTOS;
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Override
    public String getToken(User user) {
        return createToken(new HashMap<>(), user);
    }

    @Override
    public String createToken(Map<String, Object> extraClaim, User user) {
        return Jwts.builder()
                .claims(extraClaim)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1_000 * 60 * MINUTOS))
                .signWith(getKey())
                .compact();

    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
