package com.example.springboot_practice.controller.api;

import com.example.springboot_practice.ifs.CrudInterface;
import com.example.springboot_practice.model.network.Header;
import com.example.springboot_practice.model.network.request.ItemApiRequest;
import com.example.springboot_practice.model.network.response.ItemApiResponse;
import com.example.springboot_practice.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j // log
@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("") // api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // api/item/{id}
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("") // api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // api/item/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
