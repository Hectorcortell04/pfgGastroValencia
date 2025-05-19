package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.MembershipLevelDTO;
import com.example.gastroValenciaApi.models.MembershipLevelModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembershipLevelMapper {
    MembershipLevelDTO toDTO(MembershipLevelModel entity);

    MembershipLevelModel toEntity(MembershipLevelDTO dto);
}