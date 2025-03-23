package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.dtos.RestaurantDTO;
import com.example.gastroValenciaApi.services.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Optional<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO dto) {
        RestaurantDTO saved = restaurantService.saveRestaurant(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        try {
            String msg = restaurantService.deleteRestaurant(id);
            return ResponseEntity.ok(Map.of("message", msg));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error del servidor"));
        }
    }
}
