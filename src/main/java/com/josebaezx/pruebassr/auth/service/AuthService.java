package com.josebaezx.pruebassr.auth.service;

import com.josebaezx.pruebassr.auth.model.dto.AuthResponse;
import com.josebaezx.pruebassr.auth.model.dto.LoginRequest;
import com.josebaezx.pruebassr.auth.model.dto.RegisterRequest;
import com.josebaezx.pruebassr.auth.model.entity.User;
import com.josebaezx.pruebassr.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .salt(request.getSalt())
                .hash(request.getHash())
                .role(request.getRole())
                .build();
        userRepository.save(user);

        return AuthResponse
                .builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
