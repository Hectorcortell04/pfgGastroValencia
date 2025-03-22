package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.models.EventModel;
import com.example.gastroValenciaApi.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventModel> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<EventModel> getEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public EventModel saveEvent(@RequestBody EventModel event) {
        return eventService.saveEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
    }
}
