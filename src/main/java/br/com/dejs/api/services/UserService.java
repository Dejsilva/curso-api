package br.com.dejs.api.services;

import br.com.dejs.api.domain.User;
import br.com.dejs.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
    User update(UserDTO obj);

}
