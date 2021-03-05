package com.reservation.demo.service;

import com.reservation.demo.domain.Restaurant;
import com.reservation.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant get(Long id) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Bob zip");
        restaurant.setAddress("seoul");
        restaurantRepository.save(restaurant);

        Restaurant newRestaurant = restaurantRepository.findById(id).orElse(null);

        System.out.println(newRestaurant);
        return newRestaurant;
    }
}
