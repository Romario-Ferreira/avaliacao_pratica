package com.francaemp.avaliacao_pratica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Person API", version = "1.0", description = "Management Person API"))
public class AvaliacaoPraticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoPraticaApplication.class, args);
	}

}
