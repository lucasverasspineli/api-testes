package br.com.projectdicasdev.api.services;

import java.util.List;

import br.com.projectdicasdev.api.domain.User;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();
}
