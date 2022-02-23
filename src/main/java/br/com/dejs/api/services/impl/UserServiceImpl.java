package br.com.dejs.api.services.impl;

import br.com.dejs.api.domain.User;
import br.com.dejs.api.repositories.UserRepository;
import br.com.dejs.api.services.UserService;
import br.com.dejs.api.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));
    }
}
