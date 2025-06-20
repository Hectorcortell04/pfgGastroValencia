// RestaurantController.java
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurantsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(restaurantService.getAllRestaurantsByUser(userId));
    }



    @GetMapping("/{restaurantId}/{userId}")
    public ResponseEntity<RestaurantDTO> getRestaurantByIdAndUser(
            @PathVariable Long restaurantId,
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(restaurantService.getRestaurantByIdAndUser(restaurantId, userId));
    }


    @PostMapping
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO dto) {
        RestaurantDTO saved = restaurantService.saveRestaurant(dto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO dto) {
        try {
            RestaurantDTO updated = restaurantService.updateRestaurant(id, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al actualizar restaurante"));
        }
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

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDTO>> searchByName(
            @RequestParam("name") String name) {
        return ResponseEntity.ok(restaurantService.searchName(name));
    }
}