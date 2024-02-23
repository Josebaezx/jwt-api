package com.josebaezx.pruebassr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarvelApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarvelApiApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder){
//		return args -> {
//            System.out.println(passwordEncoder.encode("Admin++"));
//			System.out.println(passwordEncoder.encode("User++"));
//        };
//	}

}
