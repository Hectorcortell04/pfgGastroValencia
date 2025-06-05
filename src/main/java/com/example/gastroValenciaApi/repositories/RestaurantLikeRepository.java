package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.RestaurantLikeModel;
import com.example.gastroValenciaApi.models.RestaurantModel;
import com.example.gastroValenciaApi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantLikeRepository extends JpaRepository<RestaurantLikeModel, Long> {

    Optional<RestaurantLikeModel> findByUserAndRestaurant(UserModel user, RestaurantModel restaurant);

    List<RestaurantLikeModel> findByUser(UserModel user);

    List<RestaurantLikeModel> findByRestaurant(RestaurantModel restaurant);

    List<RestaurantLikeModel> findByUserId(Long userId);

}