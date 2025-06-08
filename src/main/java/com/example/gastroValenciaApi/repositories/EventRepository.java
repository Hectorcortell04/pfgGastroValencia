package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.EventLikeModel;
import com.example.gastroValenciaApi.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {
    // Busca eventos cuyo nombre/título contenga 'query' (ignore mayúsculas/minúsculas)
    List<EventModel> findByNameContainingIgnoreCase(String query);
}