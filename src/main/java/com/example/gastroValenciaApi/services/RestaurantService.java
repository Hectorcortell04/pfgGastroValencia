package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.models.RestaurantModel;
import com.example.gastroValenciaApi.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }



    public List<RestaurantModel> getAllRestaurants() {
        return restaurantRepository.findAll();

    }

    public Optional<RestaurantModel> getRestaurantById(int id) {
        return restaurantRepository.findById(id);
    }

    public RestaurantModel saveRestaurant(RestaurantModel restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(int id) {
        restaurantRepository.deleteById(id);
    }
}
