package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.EventDTO;
import com.example.gastroValenciaApi.models.EventModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO toDTO(EventModel event);

    EventModel toEntity(EventDTO dto);
}
