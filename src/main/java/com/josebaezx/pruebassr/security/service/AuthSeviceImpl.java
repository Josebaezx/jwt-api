package com.josebaezx.pruebassr.security.service;

import com.josebaezx.pruebassr.security.model.dto.AuthResponse;
import com.josebaezx.pruebassr.security.model.dto.LoginRequest;
import com.josebaezx.pruebassr.security.model.dto.RegisterRequest;
import com.josebaezx.pruebassr.security.model.dto.Role;
import com.josebaezx.pruebassr.security.model.entity.User;
import com.josebaezx.pruebassr.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthSeviceImpl implements AuthService{
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
