package br.com.dejs.api.services;

import br.com.dejs.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
