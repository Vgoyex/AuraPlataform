package com.example.auraPlataform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AuraPlataformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuraPlataformApplication.class, args);
	}

}
