package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.EventLikeDTO;
import com.example.gastroValenciaApi.models.EventLikeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventLikeMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    EventLikeDTO toDTO(EventLikeModel model);

    // Si en el futuro necesito inversa:
    // @Mapping(source = "userId", target = "user.id")
    // @Mapping(source = "eventId", target = "event.id")
    // EventLikeModel toModel(EventLikeDTO dto);
}
