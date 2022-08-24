package br.com.projectdicasdev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projectdicasdev.api.domain.User;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		
		User user = new User(1,"Lucas","lucas.veras@gmail.com","lucas123");
		
	}

}
