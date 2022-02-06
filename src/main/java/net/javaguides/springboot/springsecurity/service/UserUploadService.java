package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.entity.User;

public interface UserUploadService {

    User getByEmail(String email);

    void save(User user);
}
