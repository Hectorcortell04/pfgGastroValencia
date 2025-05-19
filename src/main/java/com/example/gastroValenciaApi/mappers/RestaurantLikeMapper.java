package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.RestaurantLikeDTO;
import com.example.gastroValenciaApi.models.RestaurantLikeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantLikeMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    RestaurantLikeDTO toDTO(RestaurantLikeModel model);

    // si en el futuro necesito el inverso:
    // @Mapping(source = "userId", target = "user.id")
    // @Mapping(source = "restaurantId", target = "restaurant.id")
    // RestaurantLikeModel toModel(RestaurantLikeDTO dto);
}