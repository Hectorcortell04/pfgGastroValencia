package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {
    // Busca restaurantes cuyo nombre contenga 'query' (ignora mayúsculas/minúsculas)
    List<RestaurantModel> findByNameContainingIgnoreCase(String query);


}