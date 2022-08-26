package br.com.projectdicasdev.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.repository.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	@Autowired
	private UserRepository repository;
	@Bean
	public void startDB() {
		User u1 = new User(null,"Lucas","lucasveras@gmail.com","123");
		User u2 = new User(null,"Thiago","thiago11@gmail.com","321");
		
		repository.saveAll(List.of(u1,u2));
	}
}
