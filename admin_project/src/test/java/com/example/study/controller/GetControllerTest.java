package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GetControllerTest {

    @Autowired
    private GetController getController;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(getController).build();
    }

    @Test
    void getParameter() {
        getController.getParameter("1", "123");
    }

    @Test
    void getParameter_2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getParameter?id=1&password=aaa"))
                .andExpect(status().isOk());
    }

    @Test
    void getMultiParameter() {
        System.out.println(getController.getParameter(new SearchParam("aaa", "bbb", 10)));
    }

    @Test
    void getMultiParameter_2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getMultiParameter?account=abcd&email=bcda&page=10"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}