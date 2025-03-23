package com.example.gastroValenciaApi.mappers;


import com.example.gastroValenciaApi.dtos.EventDTO;
import com.example.gastroValenciaApi.models.EventModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "localDateToString")
    EventDTO toDTO(EventModel event);

    @InheritInverseConfiguration
    @Mapping(target = "date", source = "date", qualifiedByName = "stringToLocalDate")
    EventModel toEntity(EventDTO dto);

    @Named("localDateToString")
    static String localDateToString(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }

    @Named("stringToLocalDate")
    static LocalDate stringToLocalDate(String date) {
        return date != null ? LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }
}
