package com.example.springboot_practice.controller.api;

import com.example.springboot_practice.controller.CrudController;
import com.example.springboot_practice.ifs.CrudInterface;
import com.example.springboot_practice.model.entity.Item;
import com.example.springboot_practice.model.network.Header;
import com.example.springboot_practice.model.network.request.ItemApiRequest;
import com.example.springboot_practice.model.network.response.ItemApiResponse;
import com.example.springboot_practice.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j // log
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}
