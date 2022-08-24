package br.com.projectdicasdev.api.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.repository.UserRepository;
import br.com.projectdicasdev.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findById(Integer id) {
		
		Optional<User> obj = userRepository.findById(id);
		//Para retornar um valor se não Nullo!
		return obj.orElse(null);
	}

}
