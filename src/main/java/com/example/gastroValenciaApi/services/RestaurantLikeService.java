package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.models.RestaurantLikeModel;
import com.example.gastroValenciaApi.models.RestaurantModel;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.repositories.RestaurantLikeRepository;
import com.example.gastroValenciaApi.repositories.RestaurantRepository;
import com.example.gastroValenciaApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantLikeService {

    private final RestaurantLikeRepository restaurantLikeRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantLikeService(
            RestaurantLikeRepository restaurantLikeRepository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.restaurantLikeRepository = restaurantLikeRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public String toggleLike(Long userId, Long restaurantId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        RestaurantModel restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));

        return restaurantLikeRepository.findByUserAndRestaurant(user, restaurant)
                .map(existingLike -> {
                    restaurantLikeRepository.delete(existingLike);
                    return "Like eliminado";
                })
                .orElseGet(() -> {
                    RestaurantLikeModel like = new RestaurantLikeModel();
                    like.setUser(user);
                    like.setRestaurant(restaurant);
                    restaurantLikeRepository.save(like);
                    return "Like añadido";
                });
    }

    public List<RestaurantLikeModel> getLikesByUser(Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return restaurantLikeRepository.findByUser(user);
    }

    public List<RestaurantLikeModel> getLikesByRestaurant(Long restaurantId) {
        RestaurantModel restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));
        return restaurantLikeRepository.findByRestaurant(restaurant);
    }

    public List<RestaurantLikeModel> findAll() {
        return restaurantLikeRepository.findAll();
    }
}
