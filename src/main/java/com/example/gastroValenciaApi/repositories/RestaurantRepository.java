package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {
    // Busca restaurantes cuyo nombre contenga 'query' (ignore mayúsculas/minúsculas)
    List<RestaurantModel> findByNameContainingIgnoreCase(String query);
}

