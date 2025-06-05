package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.EventDTO;
import com.example.gastroValenciaApi.models.EventModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "eventImage", target = "eventImage")
    @Mapping(source = "eventWeb", target = "eventWeb")
    EventDTO toDTO(EventModel model);

    @InheritInverseConfiguration
    EventModel toEntity(EventDTO dto);
}

