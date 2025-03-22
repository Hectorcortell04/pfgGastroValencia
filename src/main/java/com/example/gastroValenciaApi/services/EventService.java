package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.models.EventModel;
import com.example.gastroValenciaApi.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventModel> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<EventModel> getEventById(int id) {
        return eventRepository.findById(id);
    }

    public EventModel saveEvent(EventModel event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
}
