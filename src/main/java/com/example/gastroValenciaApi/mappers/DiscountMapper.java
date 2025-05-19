package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.DiscountDTO;
import com.example.gastroValenciaApi.models.DiscountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "membershipLevel.id", target = "membershipLevelId")
    DiscountDTO toDTO(DiscountModel entity);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "membershipLevel.id", source = "membershipLevelId")
    DiscountModel toEntity(DiscountDTO dto);
}