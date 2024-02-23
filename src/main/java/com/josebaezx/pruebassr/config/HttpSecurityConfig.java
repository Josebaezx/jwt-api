package com.josebaezx.pruebassr.config;

import com.josebaezx.pruebassr.utils.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class HttpSecurityConfig {
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf( csrf -> csrf.disable())
                .sessionManagement( sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider( authenticationProvider)
                .authorizeHttpRequests(authconfig -> {
                     authconfig.requestMatchers(HttpMethod.POST,"/auth/login").permitAll();
                    authconfig.requestMatchers(HttpMethod.GET,"/auth/bienvenida").permitAll();
                     authconfig.requestMatchers("/error").permitAll();
                     authconfig.requestMatchers(HttpMethod.GET,"/products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                     authconfig.requestMatchers(HttpMethod.POST,"/products").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
                     authconfig.anyRequest().denyAll();
                });
        return http.build();
    }
}
