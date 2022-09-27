package br.com.projectdicasdev.api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.domain.dto.UserDTO;
import br.com.projectdicasdev.api.repository.UserRepository;
import br.com.projectdicasdev.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {
	
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

//	@Test
//	void testFindAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCreate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAtualizar() {
//		fail("Not yet implemented");
//	}
//
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
