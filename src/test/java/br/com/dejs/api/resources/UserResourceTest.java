package br.com.dejs.api.resources;

import br.com.dejs.api.domain.User;
import br.com.dejs.api.domain.dto.UserDTO;
import br.com.dejs.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
    void whenFindByIdThenReturnSuccess() {
        Mockito.when(service.findById(Mockito.anyInt())).thenReturn(user);
        Mockito.when(mapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(userDto);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(SENHA, response.getBody().getSenha());
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(userDto);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(SENHA, response.getBody().get(INDEX).getSenha());
    }

    @Test
    void whenCreateThenReturnCreated() {
    when(service.create(ArgumentMatchers.any())).thenReturn(user);

    ResponseEntity<UserDTO> response = resource.create(userDto);

    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getHeaders().get("Location"));

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
