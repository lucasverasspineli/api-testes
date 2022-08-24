package br.com.projectdicasdev.api.services;

import br.com.projectdicasdev.api.domain.User;

public interface UserService {

	User findById(Integer id);
}
