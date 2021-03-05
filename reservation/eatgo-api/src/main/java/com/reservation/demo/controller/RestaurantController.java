package com.reservation.demo.controller;

import com.reservation.demo.domain.Restaurant;
import com.reservation.demo.repository.RestaurantRepository;
import com.reservation.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable Long id) {
        return restaurantService.get(id);
    }
}
