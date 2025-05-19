package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.dtos.MembershipLevelDTO;
import com.example.gastroValenciaApi.services.MembershipLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/membership-levels")
public class MembershipLevelController {

    private final MembershipLevelService service;

    public MembershipLevelController(MembershipLevelService service) {
        this.service = service;
    }

    @GetMapping
    public List<MembershipLevelDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<MembershipLevelDTO> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<MembershipLevelDTO> save(@RequestBody MembershipLevelDTO dto) {
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