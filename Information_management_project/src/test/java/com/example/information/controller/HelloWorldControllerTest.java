package com.example.information.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HelloWorldControllerTest {

    @Autowired
    private HelloWorldController helloWorldController;

    private MockMvc mockMvc;

    @Test
    public void helloWorld() {
        System.out.println(helloWorldController.helloWorld());
    }

    @Test
    public void mockMvcHelloWorld() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/helloWorld"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello World"));
    }
}