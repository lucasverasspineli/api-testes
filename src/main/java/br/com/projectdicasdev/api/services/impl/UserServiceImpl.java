package br.com.projectdicasdev.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.domain.dto.UserDTO;
import br.com.projectdicasdev.api.repository.UserRepository;
import br.com.projectdicasdev.api.services.UserService;
import br.com.projectdicasdev.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Integer id) {
		
		Optional<User> obj = userRepository.findById(id);
		//Para retornar um valor se não Nullo!
//		return obj.orElse(null);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User create(UserDTO obj) {
		return userRepository.save(mapper.map(obj, User.class));
	}
	
}
