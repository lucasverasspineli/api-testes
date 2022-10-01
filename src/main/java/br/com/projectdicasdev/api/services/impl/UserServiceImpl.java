package br.com.projectdicasdev.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.domain.dto.UserDTO;
import br.com.projectdicasdev.api.repository.UserRepository;
import br.com.projectdicasdev.api.services.UserService;
import br.com.projectdicasdev.api.services.exceptions.DataIntegratyViolationException;
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
//		Criando uma validação na hora que cria o email
		findByEmail(obj);
		return userRepository.save(mapper.map(obj, User.class));
	}

	//Criando um método para averiguar se já existe o email!(Método interno)
	private void findByEmail(UserDTO obj) {
		Optional<User> user = userRepository.findByEmail(obj.getEmail());
		if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
			 throw new DataIntegratyViolationException("já existe este email cadastrado");
		}
	}
	@Override
	public User update(UserDTO obj) {
		findByEmail(obj);
		return userRepository.save(mapper.map(obj,User.class));
	}
	
	@Override
	public void delete(Integer id) {
		findById(id);
		userRepository.deleteById(id);
	}
}
