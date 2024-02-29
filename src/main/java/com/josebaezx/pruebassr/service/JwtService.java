package com.josebaezx.pruebassr.service;

import com.josebaezx.pruebassr.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${security.jwt.expiration.minutes}")
    private long EXPIRATE_MINUTES;
    @Value("${security.jwt.private-key}")
    private String PRIVATE_KEY;

    public String generateJWT(User user, Map<String, Object> stringObjectMap) {
        Date issuedAt = new Date(System.currentTimeMillis());

        Date expiresAt = new Date(issuedAt.getTime() + (EXPIRATE_MINUTES  *60 * 1_000));
        return Jwts.builder()
                .setClaims(stringObjectMap)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey() {
        byte[] keyByte = Decoders.BASE64.decode(PRIVATE_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String getSubjectFromJWT(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();

    }
}
