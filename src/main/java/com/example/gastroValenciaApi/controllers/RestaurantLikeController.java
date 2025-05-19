package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.dtos.RestaurantLikeDTO;
import com.example.gastroValenciaApi.mappers.RestaurantLikeMapper;
import com.example.gastroValenciaApi.services.RestaurantLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant_likes")
public class RestaurantLikeController {

    private final RestaurantLikeService restaurantLikeService;
    private final RestaurantLikeMapper restaurantLikeMapper;

    public RestaurantLikeController(
            RestaurantLikeService restaurantLikeService,
            RestaurantLikeMapper restaurantLikeMapper
    ) {
        this.restaurantLikeService = restaurantLikeService;
        this.restaurantLikeMapper = restaurantLikeMapper;
    }

    // 👉 Añadir o quitar like a un restaurante
    @PostMapping("/{restaurantId}/like/{userId}")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long restaurantId,
            @PathVariable Long userId
    ) {
        String result = restaurantLikeService.toggleLike(userId, restaurantId);
        return ResponseEntity.ok(result);
    }

    // 👉 Obtener TODOS los likes
    @GetMapping
    public ResponseEntity<List<RestaurantLikeDTO>> getAllLikes() {
        List<RestaurantLikeDTO> dtos = restaurantLikeService.findAll()
                .stream()
                .map(restaurantLikeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 👉 Obtener todos los likes de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RestaurantLikeDTO>> getLikesByUser(@PathVariable Long userId) {
        List<RestaurantLikeDTO> dtos = restaurantLikeService.getLikesByUser(userId)
                .stream()
                .map(restaurantLikeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 👉 Obtener todos los likes de un restaurante
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<RestaurantLikeDTO>> getLikesByRestaurant(@PathVariable Long restaurantId) {
        List<RestaurantLikeDTO> dtos = restaurantLikeService.getLikesByRestaurant(restaurantId)
                .stream()
                .map(restaurantLikeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
