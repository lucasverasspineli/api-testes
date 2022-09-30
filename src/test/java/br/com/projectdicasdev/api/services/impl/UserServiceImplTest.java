package br.com.projectdicasdev.api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.sym.Name;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.domain.dto.UserDTO;
import br.com.projectdicasdev.api.repository.UserRepository;
import br.com.projectdicasdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.projectdicasdev.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {
	
	private static final String JA_EXISTE_ESTE_EMAIL_CADASTRADO = "já existe este email cadastrado";

	private static final int INDEX = 0;

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

	private static final Integer ID = 1;
	
	private static final String NAME = "Lucas";
	
	private static final String EMAIL = "LucasVVeras@gmail.com";
	
	private static final String PASSWORD = "15236";




	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	
	private UserDTO userDTO;
	
	private Optional<User> optionalUser;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this); 
		startUser();
	}

	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		when(userRepository.findById(anyInt())).thenReturn(optionalUser);
		
		
		
		User response = service.findById(ID);
		
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}
	       
	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			 service.findById(ID);
		} catch (Exception ex) {
			assertEquals(ObjectNotFoundException.class, ex.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
			
		}
		
	}

	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		when(userRepository.findAll()).thenReturn(List.of(user));
		
		List<User> listUser = service.findAll();
		assertNotNull(listUser);
		//Assegurando que o tamanho da Lista seja 1, já que foi adicionado só um Usuário
		assertEquals(1, listUser.size());
		//Assegurando que o índice 0 da Lista seja da Classe User
		assertEquals(User.class, listUser.get(INDEX).getClass());
		assertEquals(ID, listUser.get(INDEX).getId());
		assertEquals(NAME, listUser.get(INDEX).getName());
		assertEquals(EMAIL, listUser.get(INDEX).getEmail());
		assertEquals(PASSWORD, listUser.get(INDEX).getPassword());
	}

	@Test
	void whenCreateThenReturnSucess() {
		when(userRepository.save(any())).thenReturn(user);
		
		User response = service.create(userDTO);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}

	
	@Test
	void whenCreateThenReturnAnDataIntegratyViolationException() {
		when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
		
		try {
			//Alterar ID para ser validade na Exception
			 optionalUser.get().setId(2);
			 service.create(userDTO);
		} catch (Exception ex) {
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
			assertEquals(JA_EXISTE_ESTE_EMAIL_CADASTRADO, ex.getMessage());
		}
	}
	
	
	@Test
	void whenUpdateThenReturnSucess() {
		when(userRepository.save(any())).thenReturn(user);
		
		User response = service.update(userDTO);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}
	
	@Test
	void whenUpdateThenReturnAnDataIntegratyViolationException() {
		when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
		
		try {
			//Alterar ID para ser validade na Exception
			 optionalUser.get().setId(2);
			 service.create(userDTO);
		} catch (Exception ex) {
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
			assertEquals(JA_EXISTE_ESTE_EMAIL_CADASTRADO, ex.getMessage());
		}
	}
	
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}

	
}
