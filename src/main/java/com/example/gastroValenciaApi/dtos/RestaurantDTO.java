package com.example.gastroValenciaApi.dtos;

import com.example.gastroValenciaApi.enums.RestaurantType;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private RestaurantType foodType;
    private String address;
    private Double rating;
    private Double averagePrice;
    private List<String> restaurantImages;
    private String menuImage;
    private String description;
}