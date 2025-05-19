package com.example.gastroValenciaApi.dtos;

import lombok.Data;

@Data
public class RestaurantLikeDTO {
    private Long id;
    private Long userId;
    private Long restaurantId;
}