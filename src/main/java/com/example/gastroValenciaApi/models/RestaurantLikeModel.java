package com.example.gastroValenciaApi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "restaurant_likes")
public class RestaurantLikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;
}