package br.com.dejs.api.resources;

import br.com.dejs.api.domain.User;
import br.com.dejs.api.domain.dto.UserDTO;
import br.com.dejs.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID   = 1;
    public static final String NAME  = "Yuri";
    public static final String EMAIL = "yuri@email.com";
    public static final String SENHA = "123";
    public static final int INDEX = 0;
    public static final String E_MAIL_JÁ_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    public static final String E_MAIL_JÁ_CADASTRADO_NO_SISTEMA1 = "E-mail já cadastrado no sistema";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto nao encontrado";

    private User user;
    private UserDTO userDto;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
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
        userDto = new UserDTO(1, NAME, EMAIL, SENHA);
    }

}
