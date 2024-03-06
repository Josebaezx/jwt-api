package com.josebaezx.pruebassr.config;

import com.josebaezx.pruebassr.utils.validations.UserValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
    @Bean
    public UserValidations userValidations() {
        return new UserValidations();
    }
}
