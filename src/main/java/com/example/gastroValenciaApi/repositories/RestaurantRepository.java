package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {


}
