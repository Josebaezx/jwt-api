package com.josebaezx.pruebassr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("GET","PUT","POST","DELETE")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders("Origin","Content-Type","Accept","Autorization")
                .allowCredentials(true)
                .maxAge(3600);
        registry
                .addMapping("/auth/**")
                .allowedMethods("GET","PUT","POST","DELETE")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders("Origin","Content-Type","Accept","Autorization")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
