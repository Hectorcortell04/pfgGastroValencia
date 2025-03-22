package com.example.gastroValenciaApi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "restaurants")
public class RestaurantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false, length = 100)
    private String food_type;

    @Column
    private Double rating;

    @Column(name = "average_price")
    private Double average_price;

    @Column(name = "restaurant_image")
    private String restaurant_image;

    @Column(name = "meal_image")
    private String meal_image;

    @Column(columnDefinition = "TEXT")
    private String description;

}
