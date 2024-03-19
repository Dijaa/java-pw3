package com.example.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class PrimeiroProjetoApplication {
	public static void main(String[] args) {
		// System.out.println(SpringVersion.getVersion());
		SpringApplication.run(PrimeiroProjetoApplication.class, args);
	}

}
