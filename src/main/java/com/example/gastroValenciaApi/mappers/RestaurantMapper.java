package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.RestaurantDTO;
import com.example.gastroValenciaApi.models.RestaurantModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(source = "restaurantWeb", target = "restaurantWeb")
    RestaurantDTO toDTO(RestaurantModel restaurant);

    @InheritInverseConfiguration
    RestaurantModel toEntity(RestaurantDTO dto);
}
