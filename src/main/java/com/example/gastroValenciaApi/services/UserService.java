package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.DiscountDTO;
import com.example.gastroValenciaApi.dtos.UserDTO;
import com.example.gastroValenciaApi.mappers.UserMapper;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.repositories.MembershipLevelRepository;
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
    private final DiscountService discountService;
    private final MembershipLevelRepository membershipLevelRepository;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       DiscountService discountService,
                       MembershipLevelRepository membershipLevelRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.discountService = discountService;
        this.membershipLevelRepository = membershipLevelRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    public UserDTO saveUser(UserDTO dto) {
        UserModel model = userMapper.toEntity(dto);
        UserModel saved = userRepository.save(model);
        return userMapper.toDTO(saved);
    }

    public UserDTO updateUser(UserDTO dto) {
        Optional<UserModel> existingUser = userRepository.findById(dto.getId());

        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario con ID " + dto.getId() + " no existe.");
        }

        UserModel modelToUpdate = existingUser.get();

        // Campos editables
        modelToUpdate.setName(dto.getName());
        modelToUpdate.setUserImage(dto.getUserImage());

        UserModel saved = userRepository.save(modelToUpdate);
        return userMapper.toDTO(saved);
    }


    public Optional<UserDTO> getByFirebaseUid(String firebaseUid) {
        return userRepository.findByFirebaseUid(firebaseUid)
                .map(userMapper::toDTO);
    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
        return "El usuario con ID " + id + " ha sido eliminado";
    }

    private void assignDefaultDiscountsToUser(Long userId) {
        List<String> defaultDiscounts = List.of(
                "Descuento 30% en tu primer evento",
                "Descuento 10% primera reserva",
                "Envío gratis usuarios nivel Basic",
                "Descuento 50% verano",
                "Código descuento DESC30"
        );

        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setUserId(userId);
        discountDTO.setMembershipLevelId(1L);
        discountDTO.setDiscounts(defaultDiscounts);

        discountService.updateOrCreateDiscount(userId, discountDTO);
    }

    public UserDTO registerUserWithoutAuth(UserDTO dto) {
        UserModel model = userMapper.toEntity(dto);
        model.setRegistrationDate(LocalDateTime.now());
        UserModel saved = userRepository.save(model);

        assignDefaultDiscountsToUser(saved.getId());

        return userMapper.toDTO(saved);
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

        UserModel savedUser = userRepository.save(newUser);

        assignDefaultDiscountsToUser(savedUser.getId());

        return userMapper.toDTO(savedUser);
    }
}
