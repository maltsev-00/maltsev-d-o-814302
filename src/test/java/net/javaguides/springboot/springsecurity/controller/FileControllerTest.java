package net.javaguides.springboot.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void testAllFiles() throws Exception {
        mockMvc.perform(get("/files"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFile() throws Exception {
        long id = 2L;
        mockMvc.perform(delete("/files/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFile() throws Exception {
        long id = 2L;
        mockMvc.perform(get("/files/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testChangePrivacy() throws Exception {
        long id = 2L;
        mockMvc.perform(get("/files/privacy" + id))
                .andExpect(status().isOk());
    }

}