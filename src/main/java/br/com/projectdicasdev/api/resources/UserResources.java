package br.com.projectdicasdev.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.services.impl.UserServiceImpl;

@RestController
@RequestMapping(value="/user")
public class UserResources {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	
}
