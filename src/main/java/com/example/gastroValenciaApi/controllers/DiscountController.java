package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.dtos.DiscountDTO;
import com.example.gastroValenciaApi.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService service;

    public DiscountController(DiscountService service) {
        this.service = service;
    }

    @GetMapping
    public List<DiscountDTO> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<DiscountDTO> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId) {
        Optional<DiscountDTO> dto = service.getByUserId(userId);
        if (dto.isPresent()) { //Lo he hecho con if porque sinó daba error el ResponseEntity con el Optional.Map
            return ResponseEntity.ok(dto.get());
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "No se encontró descuento para el usuario"));
        }
    }

    @PostMapping
    public ResponseEntity<DiscountDTO> save(@RequestBody DiscountDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(Map.of("message", service.delete(id)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error servidor"));
        }
    }
}
