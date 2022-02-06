package net.javaguides.springboot.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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

    long id = 2L;
    String name = "jpg";

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAllFiles() throws Exception {
        mockMvc.perform(get("/files"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeleteFile() throws Exception {
        mockMvc.perform(delete("/files/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetFile() throws Exception {
        mockMvc.perform(get("/files/" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testChangePrivacy() throws Exception {
        mockMvc.perform(get("/files/privacy/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testSortByName() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/files/privacy/" + name))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }
}
