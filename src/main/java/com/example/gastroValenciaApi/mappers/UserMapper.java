package com.example.gastroValenciaApi.mappers;

import com.example.gastroValenciaApi.dtos.UserDTO;
import com.example.gastroValenciaApi.models.UserModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firebaseUid", target = "firebaseUid")
    @Mapping(source = "registrationDate", target = "registrationDate")
    UserDTO toDTO(UserModel model);

    @InheritInverseConfiguration
    UserModel toEntity(UserDTO dto);
}
