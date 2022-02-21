package net.javaguides.springboot.springsecurity.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.dto.LoginRequest;
import net.javaguides.springboot.springsecurity.model.dto.UserRegistrationDto;
import net.javaguides.springboot.springsecurity.model.entity.User;
import net.javaguides.springboot.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @Value("$(jwt.secret)")
    private String jwtSecret;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmailAndPassword(loginRequest.getLogin(), loginRequest.getPassword());
        if (user != null) {
            return new ResponseEntity<>(generateToken(user.getEmail()), HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> registerUserAccount(@RequestBody UserRegistrationDto userDto) {

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            return new ResponseEntity<>("User is found", HttpStatus.NOT_FOUND);
        }
        userService.save(userDto);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
