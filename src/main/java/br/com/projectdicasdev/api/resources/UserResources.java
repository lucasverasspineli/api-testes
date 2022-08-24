package br.com.projectdicasdev.api.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projectdicasdev.api.domain.User;

@RestController
@RequestMapping(value="/user")
public class UserResources {
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(new User(1,"Lucas","lucas.veras@gmail.com","lucas123"));
	}
}
