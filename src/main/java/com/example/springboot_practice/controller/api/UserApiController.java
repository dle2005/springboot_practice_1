package com.example.springboot_practice.controller.api;

import com.example.springboot_practice.ifs.CrudInterface;
import com.example.springboot_practice.model.network.Header;
import com.example.springboot_practice.model.network.request.UserApiRequest;
import com.example.springboot_practice.model.network.response.UserApiResponse;
import com.example.springboot_practice.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j // log
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // /api/uiser
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request); // -> request.toString()
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {

        return null;
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> userApiRequest) {

        return null;
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(Long id) {

        return null;
    }
}
