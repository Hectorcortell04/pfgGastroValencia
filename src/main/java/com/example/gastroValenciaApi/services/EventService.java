package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.EventDTO;
import com.example.gastroValenciaApi.mappers.EventMapper;
import com.example.gastroValenciaApi.models.EventModel;
import com.example.gastroValenciaApi.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<EventDTO> getEventById(int id) {
        return eventRepository.findById(id).map(eventMapper::toDTO);
    }

    public EventDTO saveEvent(EventDTO dto) {
        EventModel event = eventMapper.toEntity(dto);
        EventModel saved = eventRepository.save(event);
        return eventMapper.toDTO(saved);
    }

    public String deleteEvent(int id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Does not found");
        }
        eventRepository.deleteById(id);
        return "El usuario con ID" + id + "ha sido eliminado";
    }
}
