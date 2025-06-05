package com.example.gastroValenciaApi.models;

import com.example.gastroValenciaApi.enums.RestaurantType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private RestaurantType foodType;


    @Column
    private Double rating;

    @Column(name = "average_price")
    private Double averagePrice;

    @ElementCollection
    @Column(name = "restaurant_images")
    private List<String> restaurantImages;

    @Column(name = "menu_image")
    private String menuImage;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "restaurant_web", length = 255)
    private String restaurantWeb;

}
