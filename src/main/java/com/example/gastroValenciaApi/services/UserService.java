package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.UserDTO;
import com.example.gastroValenciaApi.mappers.UserMapper;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }


    public UserDTO saveUser(UserDTO dto) {
        UserModel model = userMapper.toEntity(dto);
        UserModel saved = userRepository.save(model);
        return userMapper.toDTO(saved);
    }

    public String deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
        return "El usuario con ID" + id + "ha sido eliminado";
    }

}
