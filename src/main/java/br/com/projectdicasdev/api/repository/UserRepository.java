package br.com.projectdicasdev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.projectdicasdev.api.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
}
