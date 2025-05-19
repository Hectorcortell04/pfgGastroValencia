package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.EventLikeModel;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface EventLikeRepository extends JpaRepository<EventLikeModel, Long> {

    // Ver si un usuario ya ha dado like a un evento
    Optional<EventLikeModel> findByUserAndEvent(UserModel user, EventModel event);

    // Obtener todos los likes de un evento
    List<EventLikeModel> findByEvent(EventModel event);

    // Obtener todos los likes de un usuario
    List<EventLikeModel> findByUser(UserModel user);

    // Eliminar un like
//    void deleteByUserAndEvent(UserModel user, EventModel event);
}
