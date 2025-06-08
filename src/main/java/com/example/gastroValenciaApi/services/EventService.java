package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.EventDTO;
import com.example.gastroValenciaApi.mappers.EventMapper;
import com.example.gastroValenciaApi.models.EventLikeModel;
import com.example.gastroValenciaApi.models.EventModel;
import com.example.gastroValenciaApi.repositories.EventLikeRepository;
import com.example.gastroValenciaApi.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventLikeRepository eventLikeRepository;


    public EventService(EventRepository eventRepository, EventMapper eventMapper, EventLikeRepository eventLikeRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.eventLikeRepository = eventLikeRepository;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EventDTO> getAllEventsByUser(Long userId) {
        List<EventModel> events = eventRepository.findAll();
        List<EventLikeModel> likes = eventLikeRepository.findByUserId(userId);

        Set<Long> likedEventIds = likes.stream()
                .map(like -> like.getEvent().getId())
                .collect(Collectors.toSet());

        return events.stream().map(event -> {
            EventDTO dto = eventMapper.toDTO(event);
            dto.setLiked(likedEventIds.contains(event.getId()) ? true : false);
            return dto;
        }).collect(Collectors.toList());
    }

    public EventDTO getEventByIdAndUser(Long eventId, Long userId) {
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado con ID " + eventId));

        boolean liked = eventLikeRepository.findByUserIdAndEventId(userId, eventId).isPresent();

        EventDTO dto = eventMapper.toDTO(event);
        dto.setLiked(liked);
        return dto;
    }



    public Optional<EventDTO> getEventById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::toDTO);
    }

    public EventDTO saveEvent(EventDTO dto) {
        EventModel event = eventMapper.toEntity(dto);
        EventModel saved = eventRepository.save(event);
        return eventMapper.toDTO(saved);
    }

    public EventDTO updateEvent(Long id, EventDTO dto) {
        EventModel existing = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento con ID " + id + " no encontrado."));

        EventModel updated = eventMapper.toEntity(dto);
        updated.setId(existing.getId());

        EventModel saved = eventRepository.save(updated);
        return eventMapper.toDTO(saved);
    }

    public String deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Evento con ID " + id + " no existe.");
        }
        eventRepository.deleteById(id);
        return "El evento con ID " + id + " ha sido eliminado correctamente.";
    }


    public List<EventDTO> searchName(String texto) {
        return eventRepository
                .findByNameContainingIgnoreCase(texto)
                .stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }
}