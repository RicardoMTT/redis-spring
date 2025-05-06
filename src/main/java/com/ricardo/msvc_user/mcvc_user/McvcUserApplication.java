package com.ricardo.msvc_user.mcvc_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching// Habilita el sistema de caché en la aplicación
public class McvcUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(McvcUserApplication.class, args);
	}

}
