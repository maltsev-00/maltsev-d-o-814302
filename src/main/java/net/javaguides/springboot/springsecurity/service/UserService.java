package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.dto.UserRegistrationDto;
import net.javaguides.springboot.springsecurity.model.entity.User;

public interface UserService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
    User findByEmailAndPassword(String email, String password);
    User getByEmail(String email);

}
