package com.example.gastroValenciaApi.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String foodType;
    private String address;
    private Double rating;
    private Double averagePrice;
    private List<String> restaurantImages;
    private String menuImage;
    private String description;
}