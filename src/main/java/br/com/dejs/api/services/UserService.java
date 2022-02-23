package br.com.dejs.api.services;

import br.com.dejs.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();

}
