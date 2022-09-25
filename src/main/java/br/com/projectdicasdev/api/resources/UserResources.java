package br.com.projectdicasdev.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.domain.dto.UserDTO;
import br.com.projectdicasdev.api.services.impl.UserServiceImpl;

@RestController
@RequestMapping(value="/user")
public class UserResources {
	
	private static final String ID = "/{id}";

	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping(value = ID)
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
	
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj){		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(service.create(obj).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = ID)
	public ResponseEntity<UserDTO> atualizar(@PathVariable Integer id,@RequestBody UserDTO obj){
		obj.setId(id);
		return ResponseEntity.ok().body(mapper.map(service.update(obj), UserDTO.class));	
	}
	
	@PutMapping(value = "mod")
	public ResponseEntity<UserDTO> atualizacao(@RequestBody UserDTO obj){
		User newUser = service.atualizar(obj);
		return ResponseEntity.ok().body(mapper.map(newUser, UserDTO.class));	
	}
	
	@DeleteMapping(value = ID)
	public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
