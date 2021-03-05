package com.example.study.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostControllerTest {

    @Autowired
    private PostController postController;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    void postMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/postMethod")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"account\" : \"a\", \n" +
                        "    \"email\" : \"b\", \n" +
                        "    \"page\" : 10\n" +
                        "}")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}