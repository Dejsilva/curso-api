package br.com.dejs.api.services.impl;

import br.com.dejs.api.domain.User;
import br.com.dejs.api.domain.dto.UserDTO;
import br.com.dejs.api.repositories.UserRepository;
import br.com.dejs.api.services.exceptions.DataIntegratyViolationException;
import br.com.dejs.api.services.exceptions.ObjectNotFoundExceptions;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.web.client.ExpectedCount;

import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID   = 1;
    public static final String NAME  = "Yuri";
    public static final String EMAIL = "yuri@email.com";
    public static final String SENHA = "123";
    public static final int INDEX = 0;
    public static final String E_MAIL_JÁ_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    public static final String E_MAIL_JÁ_CADASTRADO_NO_SISTEMA1 = "E-mail já cadastrado no sistema";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto nao encontrado";
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
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundExceptions("Objeto não encontrado"));

        try {
            service.findById(ID);
        }catch(Exception ex) {
            assertEquals(ObjectNotFoundExceptions.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

       assertNotNull(response);
       assertEquals(1, response.size());
       assertEquals(User.class, response.get(INDEX).getClass());

       assertEquals(ID, response.get(INDEX).getId());
       assertEquals(NAME, response.get(INDEX).getNome());
       assertEquals(EMAIL, response.get(INDEX).getEmail());
       assertEquals(SENHA, response.get(INDEX).getSenha());
    }

    @Test
    void whenCreateThenReturnSuccess() {

        when(repository.save(ArgumentMatchers.any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(SENHA, response.getSenha());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {

        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JÁ_CADASTRADO_NO_SISTEMA1, ex.getMessage());
        }

        }

    @Test
        void whenUpdateThenReturnSuccess() {
            when(repository.save(ArgumentMatchers.any())).thenReturn(user);

            User response = service.update(userDTO);

            assertNotNull(response);
            assertEquals(User.class, response.getClass());
            assertEquals(ID, response.getId());
            assertEquals(NAME, response.getNome());
            assertEquals(EMAIL, response.getEmail());
            assertEquals(SENHA, response.getSenha());
        }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JÁ_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }


    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
       service.delete(ID);
        verify(repository, Mockito.times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWidthObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundExceptions(OBJETO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundExceptions.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }



    private void startUser() {
        user = new User(ID, NAME, EMAIL, SENHA);
        userDTO = new UserDTO(1, NAME, EMAIL, SENHA);
        optionalUser =  Optional.of(new User(1, NAME, EMAIL, SENHA));


    }

}