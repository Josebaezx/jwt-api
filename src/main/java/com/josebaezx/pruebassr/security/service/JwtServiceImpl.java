package com.josebaezx.pruebassr.security.service;

import com.josebaezx.pruebassr.security.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1_000 * 60 * 5))
                .signWith(getKey())
                .compact();

    }

    public SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, User userDetails) {
        if(userDetails == null){
            return false;
        }
        final String email = getEmailFromToken(token);
        return email.equals(userDetails.getEmail()) && !isTokenExpired(token);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Date getExpirationToken(String token){
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpirationToken(token).before(new Date());

    }
}
