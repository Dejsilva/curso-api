package br.com.dejs.api.services.impl;

import br.com.dejs.api.domain.User;
import br.com.dejs.api.domain.dto.UserDTO;
import br.com.dejs.api.repositories.UserRepository;
import br.com.dejs.api.services.UserService;
import br.com.dejs.api.services.exceptions.ObjectNotFoundExceptions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));
    }


    public List<User> findAll() {
        return repository.findAll();

    }

    @Override
    public User create(UserDTO obj) {
        return repository.save(mapper.map(obj, User.class));
    }
}
