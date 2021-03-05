package com.reservation.demo.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequest {

    private Long id;

    private String name;

    private String address;

}
