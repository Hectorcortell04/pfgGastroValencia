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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventDTO>> getAllEventsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(eventService.getAllEventsByUser(userId));
    }

    @GetMapping("/{eventId}/{userId}")
    public ResponseEntity<EventDTO> getEventByIdAndUser(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(eventService.getEventByIdAndUser(eventId, userId));
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

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO dto) {
        try {
            EventDTO updated = eventService.updateEvent(id, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventDTO>> searchByName(
            @RequestParam("name") String name) {
        return ResponseEntity.ok(eventService.searchName(name));
    }
}