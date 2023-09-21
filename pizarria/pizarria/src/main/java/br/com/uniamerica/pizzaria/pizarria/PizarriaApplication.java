package br.com.uniamerica.pizzaria.pizarria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@EntityScan(basePackages = "br/com/uniamerica/pizzaria/pizarria/repository")
public class PizarriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizarriaApplication.class, args);
	}

}
