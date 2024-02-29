package com.josebaezx.pruebassr.filter;

import com.josebaezx.pruebassr.entity.User;
import com.josebaezx.pruebassr.repository.UserRepository;
import com.josebaezx.pruebassr.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        1. Obtener el header que contiene el jwt
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

//        2. Obtener el jwt desde el header
        String jwt = authHeader.substring(7);

//        3. Obtener el subject/username desde el jwt
        String subject = jwtService.getSubjectFromJWT(jwt);

//        4. Obtener el usuario desde la base de datos
        User user = userRepository.findByUsername(subject).orElseThrow(() -> new RuntimeException("User not found"));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(subject,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        5. Asignar el usuario al SecurityContextHolder
        filterChain.doFilter(request, response);

    }
}
