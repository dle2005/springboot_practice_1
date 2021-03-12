package com.example.information.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/helloWorld")
public class HelloWorldController {

    @PostMapping("")
    public String helloWorld() {
        return "Hello World";
    }

}
