package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.dtos.EventLikeDTO;
import com.example.gastroValenciaApi.mappers.EventLikeMapper;
import com.example.gastroValenciaApi.services.EventLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event_likes")
public class EventLikeController {

    private final EventLikeService eventLikeService;
    private final EventLikeMapper eventLikeMapper;

    public EventLikeController(
            EventLikeService eventLikeService,
            EventLikeMapper eventLikeMapper
    ) {
        this.eventLikeService = eventLikeService;
        this.eventLikeMapper = eventLikeMapper;
    }

    // 👉 Marcar o desmarcar like a un evento
    @PostMapping("/{eventId}/like/{userId}")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        String result = eventLikeService.toggleLike(userId, eventId);
        return ResponseEntity.ok(result);
    }

    // 👉 Obtener TODOS los likes de eventos
    @GetMapping
    public ResponseEntity<List<EventLikeDTO>> getAllLikes() {
        List<EventLikeDTO> dtos = eventLikeService.findAll()
                .stream()
                .map(eventLikeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 👉 Obtener todos los likes que ha hecho un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventLikeDTO>> getLikesByUser(@PathVariable Long userId) {
        List<EventLikeDTO> dtos = eventLikeService.getLikesByUser(userId)
                .stream()
                .map(eventLikeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 👉 Obtener todos los likes que tiene un evento
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventLikeDTO>> getLikesByEvent(@PathVariable Long eventId) {
        List<EventLikeDTO> dtos = eventLikeService.getLikesByEvent(eventId)
                .stream()
                .map(eventLikeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}