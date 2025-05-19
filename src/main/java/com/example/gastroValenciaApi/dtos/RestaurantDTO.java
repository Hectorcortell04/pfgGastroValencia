package com.example.gastroValenciaApi.dtos;

import lombok.Data;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String foodType;
    private String address;
    private Double rating;
    private Double averagePrice;
    private String restaurantImage;
    private String menuImage;
    private String description;
}