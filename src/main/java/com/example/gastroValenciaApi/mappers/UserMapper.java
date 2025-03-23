package com.example.gastroValenciaApi.mappers;


import com.example.gastroValenciaApi.dtos.UserDTO;
import com.example.gastroValenciaApi.models.UserModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(UserModel model);


    @InheritInverseConfiguration
    UserModel toEntity(UserDTO dto);
}
