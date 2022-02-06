package net.javaguides.springboot.springsecurity.controller;

import junit.framework.TestCase;
import net.javaguides.springboot.springsecurity.model.entity.UserLogin;
import org.assertj.core.api.AssertionsForClassTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRegistrationControllerTest extends TestCase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    public void testLogin() {
        AssertionsForClassTypes
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login",
                        UserLogin.class));
    }

    public void testRegisterUserAccount() {
        AssertionsForClassTypes
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/registration",
                        Void.class));
    }
}