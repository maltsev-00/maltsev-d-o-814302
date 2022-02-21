package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.dto.UserRegistrationDto;
import net.javaguides.springboot.springsecurity.model.entity.Role;
import net.javaguides.springboot.springsecurity.model.entity.User;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserUploadService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setRoles(List.of(new Role("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        File file = new File(user.getEmail());
        file.mkdir();
        return userRepository.save(user);
    }


    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
