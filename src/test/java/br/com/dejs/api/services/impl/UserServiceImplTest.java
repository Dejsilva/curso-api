package br.com.dejs.api.services.impl;

import br.com.dejs.api.domain.User;
import br.com.dejs.api.domain.dto.UserDTO;
import br.com.dejs.api.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID   = 1;
    public static final String NAME  = "Yuri";
    public static final String EMAIL = "yuri@email.com";
    public static final String SENHA = "123";
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdTheReturnAnUserInstance() {
       when(repository.findById(anyInt())).thenReturn(optionalUser);


        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, SENHA);
        userDTO = new UserDTO(1, NAME, EMAIL, SENHA);
        optionalUser =  Optional.of(new User(1, NAME, EMAIL, SENHA));


    }

}