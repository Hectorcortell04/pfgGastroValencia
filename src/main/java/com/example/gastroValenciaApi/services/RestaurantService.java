// ───────── RestaurantService.java ─────────
package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.RestaurantDTO;
import com.example.gastroValenciaApi.mappers.RestaurantMapper;
import com.example.gastroValenciaApi.models.RestaurantLikeModel;
import com.example.gastroValenciaApi.models.RestaurantModel;
import com.example.gastroValenciaApi.repositories.RestaurantLikeRepository;
import com.example.gastroValenciaApi.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantLikeRepository restaurantLikeRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper, RestaurantLikeRepository restaurantLikeRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.restaurantLikeRepository = restaurantLikeRepository;
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RestaurantDTO> getAllRestaurantsByUser(Long userId) {
        List<RestaurantModel> restaurants = restaurantRepository.findAll();
        List<RestaurantLikeModel> likes = restaurantLikeRepository.findByUserId(userId);

        Set<Long> likedRestaurantIds = likes.stream()
                .map(like -> like.getRestaurant().getId())
                .collect(Collectors.toSet());

        return restaurants.stream().map(restaurant -> {
            RestaurantDTO dto = restaurantMapper.toDTO(restaurant);
            dto.setLiked(likedRestaurantIds.contains(restaurant.getId()));
            return dto;
        }).collect(Collectors.toList());
    }

    public RestaurantDTO getRestaurantByIdAndUser(Long restaurantId, Long userId) {
        RestaurantModel restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado con ID " + restaurantId));

        boolean liked = restaurantLikeRepository.findByUserIdAndRestaurantId(userId, restaurantId).isPresent();

        RestaurantDTO dto = restaurantMapper.toDTO(restaurant);
        dto.setLiked(liked);
        return dto;
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

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO dto) {
        Optional<RestaurantModel> existingOpt = restaurantRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró restaurante con ID " + id);
        }

        RestaurantModel existing = existingOpt.get();

        // Actualizamos los campos
        existing.setName(dto.getName());
        existing.setFoodType(dto.getFoodType());
        existing.setAddress(dto.getAddress());
        existing.setRating(dto.getRating());
        existing.setAveragePrice(dto.getAveragePrice());
        existing.setRestaurantImages(dto.getRestaurantImages());
        existing.setMenuImage(dto.getMenuImage());
        existing.setDescription(dto.getDescription());
        existing.setRestaurantWeb(dto.getRestaurantWeb());

        RestaurantModel updated = restaurantRepository.save(existing);
        return restaurantMapper.toDTO(updated);
    }


    public String deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new IllegalArgumentException("El restaurante con ID " + id + " no existe.");
        }
        restaurantRepository.deleteById(id);
        return "El restaurante con ID " + id + " ha sido eliminado correctamente.";
    }

    public List<RestaurantDTO> searchName(String texto) {
        return restaurantRepository
                .findByNameContainingIgnoreCase(texto)
                .stream()
                .map(restaurantMapper::toDTO)
                .collect(Collectors.toList());
    }
}