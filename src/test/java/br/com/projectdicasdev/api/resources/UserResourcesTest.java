package br.com.projectdicasdev.api.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.projectdicasdev.api.domain.User;
import br.com.projectdicasdev.api.domain.dto.UserDTO;
import br.com.projectdicasdev.api.services.impl.UserServiceImpl;

class UserResourcesTest {

	private static final String JA_EXISTE_ESTE_EMAIL_CADASTRADO = "já existe este email cadastrado";

	private static final int INDEX = 0;

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

	private static final Integer ID = 1;

	private static final String NAME = "Lucas";

	private static final String EMAIL = "LucasVVeras@gmail.com";

	private static final String PASSWORD = "15236";

	private User user;

	private UserDTO userDTO;

	@InjectMocks
	private UserResources resource;
	@Mock
	private ModelMapper mapper;
	@Mock
	private UserServiceImpl service;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startInstance();
	}

	@Test
	void whenFindByIdthenReturnSucess() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDTO);

		ResponseEntity<UserDTO> response = resource.findById(ID);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		// Assegura que o Objeto que passar no corpo da requisição seja da classe
		// UserDTO
		assertEquals(UserDTO.class, response.getBody().getClass());
		// Verificação dos dados no corpo da requisição
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(PASSWORD, response.getBody().getPassword());

	}

	@Test
	void whenFindAllThenReturnListUserDTO() {
		when(service.findAll()).thenReturn(List.of(user));
		when(mapper.map(any(), any())).thenReturn(userDTO);

		ResponseEntity<List<UserDTO>> listaUserDTO = resource.findAll();

		assertNotNull(listaUserDTO);
		assertNotNull(listaUserDTO.getBody());
// 		Assegurando que o Status seja OK
		assertEquals(HttpStatus.OK, listaUserDTO.getStatusCode());
		assertEquals(ResponseEntity.class, listaUserDTO.getClass());
//		Assegurando que o objeto que vem no corpo da requisição seja um ArrayList 
		assertEquals(ArrayList.class, listaUserDTO.getBody().getClass());
//		Verificando se o objeto que virá dentro do corpo da listaUserDTO será da mesma Classe que o UserDTO
		assertEquals(UserDTO.class, listaUserDTO.getBody().get(INDEX).getClass());
		assertEquals(ID, listaUserDTO.getBody().get(INDEX).getId());
		assertEquals(NAME, listaUserDTO.getBody().get(INDEX).getName());
		assertEquals(EMAIL, listaUserDTO.getBody().get(INDEX).getEmail());
		assertEquals(PASSWORD, listaUserDTO.getBody().get(INDEX).getPassword());

	}

//	@Test
//	void testCreate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAtualizar() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAtualizacao() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}

	private void startInstance() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}

}
