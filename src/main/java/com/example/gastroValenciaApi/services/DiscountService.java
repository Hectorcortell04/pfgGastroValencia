package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.DiscountDTO;
import com.example.gastroValenciaApi.mappers.DiscountMapper;
import com.example.gastroValenciaApi.models.DiscountModel;
import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.repositories.DiscountRepository;
import com.example.gastroValenciaApi.repositories.MembershipLevelRepository;
import com.example.gastroValenciaApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountService {

    private final DiscountRepository repository;
    private final DiscountMapper mapper;
    private final UserRepository userRepository;
    private final MembershipLevelRepository membershipLevelRepository;

    public DiscountService(
            DiscountRepository repository,
            DiscountMapper mapper,
            UserRepository userRepository,
            MembershipLevelRepository membershipLevelRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.membershipLevelRepository = membershipLevelRepository;
    }

    public List<DiscountDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DiscountDTO> getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    public Optional<DiscountDTO> getByUserId(Long userId) {
        return repository.findByUserId(userId).map(mapper::toDTO);
    }

    public DiscountDTO save(DiscountDTO dto) {
        DiscountModel entity = mapper.toEntity(dto);
        DiscountModel saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public DiscountDTO updateOrCreateDiscount(Long userId, DiscountDTO dto) {
        Optional<DiscountModel> existing = repository.findByUserId(userId);

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (existing.isPresent()) {
            DiscountModel model = existing.get();
            model.setMembershipLevel(
                    membershipLevelRepository.findById(dto.getMembershipLevelId()).orElse(null)
            );
            model.setDiscounts(dto.getDiscounts());
            return mapper.toDTO(repository.save(model));
        } else {
            DiscountModel model = mapper.toEntity(dto);
            model.setUser(user);
            return mapper.toDTO(repository.save(model));
        }
    }

    public String delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Descuento con ID " + id + " no existe.");
        }
        repository.deleteById(id);
        return "Descuento con ID " + id + " eliminado correctamente.";
    }
}
