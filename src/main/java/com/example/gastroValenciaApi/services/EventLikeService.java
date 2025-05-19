package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.models.EventLikeModel;
import com.example.gastroValenciaApi.models.EventModel;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.repositories.EventLikeRepository;
import com.example.gastroValenciaApi.repositories.EventRepository;
import com.example.gastroValenciaApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventLikeService {

    private final EventLikeRepository eventLikeRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public EventLikeService(
            EventLikeRepository eventLikeRepository,
            UserRepository userRepository,
            EventRepository eventRepository
    ) {
        this.eventLikeRepository = eventLikeRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
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

    public List<EventLikeModel> getLikesByUser(Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return eventLikeRepository.findByUser(user);
    }

    public List<EventLikeModel> getLikesByEvent(Long eventId) {
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
        return eventLikeRepository.findByEvent(event);
    }

    // Obtener todos los likes de evento
    public List<EventLikeModel> findAll() {
        return eventLikeRepository.findAll();
    }
}
