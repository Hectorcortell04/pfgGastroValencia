package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.DiscountDTO;
import com.example.gastroValenciaApi.models.DiscountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "membershipLevel.id", target = "membershipLevelId"),
            @Mapping(source = "discounts", target = "discounts")
    })
    DiscountDTO toDTO(DiscountModel entity);

    @Mappings({
            @Mapping(target = "user.id", source = "userId"),
            @Mapping(target = "membershipLevel.id", source = "membershipLevelId"),
            @Mapping(source = "discounts", target = "discounts")
    })
    DiscountModel toEntity(DiscountDTO dto);
}
