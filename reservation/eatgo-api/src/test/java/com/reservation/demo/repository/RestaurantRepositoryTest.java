package com.reservation.demo.repository;

import com.reservation.demo.DemoApplicationTests;
import com.reservation.demo.domain.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class RestaurantRepositoryTest extends DemoApplicationTests {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void create() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Bob zip");
        restaurant.setAddress("seoul");

        Restaurant newRestaurant = restaurantRepository.save(restaurant);
        Assertions.assertThat(newRestaurant.getName()).isEqualTo("Bob zip");
        System.out.println(newRestaurant);
    }

    @Test
    public void read() {
        Optional<Restaurant> restaurant = restaurantRepository.findById(1L);

        Assert.assertNotNull(restaurant);
        System.out.println(restaurant);
    }
}