package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.EventDTO;
import com.example.gastroValenciaApi.mappers.EventMapper;
import com.example.gastroValenciaApi.models.EventLikeModel;
import com.example.gastroValenciaApi.models.EventModel;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.repositories.EventLikeRepository;
import com.example.gastroValenciaApi.repositories.EventRepository;
import com.example.gastroValenciaApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventLikeService {

    private final EventLikeRepository eventLikeRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventLikeService(
            EventLikeRepository eventLikeRepository,
            UserRepository userRepository,
            EventRepository eventRepository, EventMapper eventMapper
    ) {
        this.eventLikeRepository = eventLikeRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    // Toggle like (añade si no existe, elimina si ya existe)
    public String toggleLike(Long userId, Long eventId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        return eventLikeRepository.findByUserAndEvent(user, event).map(existingLike -> {
            eventLikeRepository.delete(existingLike);
            return "Like eliminado";
        }).orElseGet(() -> {
            EventLikeModel newLike = new EventLikeModel();
            newLike.setUser(user);
            newLike.setEvent(event);
            eventLikeRepository.save(newLike);
            return "Like añadido";
        });
    }

    public List<EventDTO> getLikesByUser(Long userId) {
        List<EventLikeModel> likes = eventLikeRepository.findByUserId(userId);

        return likes.stream()
                .map(EventLikeModel::getEvent)
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<EventLikeModel> getLikesByEvent(Long eventId) {
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
        return eventLikeRepository.findByEvent(event);
    }

    public List<EventLikeModel> findAll() {
        return eventLikeRepository.findAll();
    }
}