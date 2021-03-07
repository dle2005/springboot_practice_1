package com.example.study.controller.api;

import com.example.study.StudyApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiControllerTest extends StudyApplicationTests {

    @Autowired
    private UserApiController userApiController;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();
    }

    @Test
    public void read() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}