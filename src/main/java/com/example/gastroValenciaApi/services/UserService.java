package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.UserDTO;
import com.example.gastroValenciaApi.mappers.UserMapper;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    public UserDTO saveUser(UserDTO dto) {
        UserModel model = userMapper.toEntity(dto);
        UserModel saved = userRepository.save(model);
        return userMapper.toDTO(saved);
    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
        return "El usuario con ID " + id + " ha sido eliminado";
    }

    public UserDTO authenticateAndSaveIfNotExists(String idToken) throws Exception {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();

        String name = decodedToken.getName();
        if (name == null || name.isBlank()) {
            name = email;
        }

        Optional<UserModel> existingUser = userRepository.findByFirebaseUid(uid);
        if (existingUser.isPresent()) {
            return userMapper.toDTO(existingUser.get());
        }

        UserModel newUser = new UserModel();
        newUser.setFirebaseUid(uid);
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setRegistrationDate(LocalDateTime.now());

        UserModel saved = userRepository.save(newUser);
        return userMapper.toDTO(saved);
    }
}
