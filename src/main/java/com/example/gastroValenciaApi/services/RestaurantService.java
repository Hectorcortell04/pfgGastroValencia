package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.RestaurantDTO;
import com.example.gastroValenciaApi.mappers.RestaurantMapper;
import com.example.gastroValenciaApi.models.RestaurantModel;
import com.example.gastroValenciaApi.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }



    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map(restaurantMapper::toDTO).collect(Collectors.toList());

    }

    public Optional<RestaurantDTO> getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::toDTO);
    }

    public RestaurantDTO saveRestaurant(RestaurantDTO dto) {
        RestaurantModel entity = restaurantMapper.toEntity(dto);
        RestaurantModel saved = restaurantRepository.save(entity);
        return restaurantMapper.toDTO(saved);
    }

    public String deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new IllegalArgumentException("El restaurante con ID" + id + " no existe.");
        }
        restaurantRepository.deleteById(id);
        return "El restaurante con ID" + id + " ha sido eliminado correctamente.";
    }
}
