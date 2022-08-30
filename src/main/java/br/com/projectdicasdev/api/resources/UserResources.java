package br.com.projectdicasdev.api.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.domain.dto.UserDTO;
import br.com.projectdicasdev.api.services.impl.UserServiceImpl;

@RestController
@RequestMapping(value="/user")
public class UserResources {
	
	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(mapper.map(service.findById(id),UserDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
//		List<User> listUser = service.findAll();
//		List<UserDTO> listDTO = listUser.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
//		List<UserDTO> listDTO = service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(service.findAll()
				.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
	}
	
}
