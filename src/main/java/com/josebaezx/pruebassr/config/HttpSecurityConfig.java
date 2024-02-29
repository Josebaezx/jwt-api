package com.josebaezx.pruebassr.config;

import com.josebaezx.pruebassr.filter.JwtAuthenticationFilter;
import com.josebaezx.pruebassr.utils.Permission;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class HttpSecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter autenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf( csrf -> csrf.disable())
                .sessionManagement( sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider( authenticationProvider)
                .addFilterBefore(autenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(getAuthRequestMachers())
        ;
        return http.build();
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> getAuthRequestMachers() {
        return authconfig -> {
            authconfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
            authconfig.requestMatchers(HttpMethod.GET, "/auth/bienvenida").permitAll();
            authconfig.requestMatchers("/error").permitAll();
            authconfig.requestMatchers(HttpMethod.GET, "/products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
            authconfig.requestMatchers(HttpMethod.POST, "/products").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
            authconfig.anyRequest().denyAll();
        };
    }
}
