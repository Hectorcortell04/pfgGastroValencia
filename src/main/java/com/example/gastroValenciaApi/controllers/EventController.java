// EventController.java
package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.dtos.EventDTO;
import com.example.gastroValenciaApi.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<EventDTO> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public ResponseEntity<EventDTO> saveEvent(@RequestBody EventDTO dto) {
        EventDTO saved = eventService.saveEvent(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            String message = eventService.deleteEvent(id);
            return ResponseEntity.ok(Map.of("message", message));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error servidor"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventDTO>> searchByName(
            @RequestParam("q") String q) {
        return ResponseEntity.ok(eventService.searchName(q));
    }
}