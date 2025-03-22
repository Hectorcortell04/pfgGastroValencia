package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Integer> {
}
