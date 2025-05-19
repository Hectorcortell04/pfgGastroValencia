package com.example.gastroValenciaApi.services;

import com.example.gastroValenciaApi.dtos.MembershipLevelDTO;
import com.example.gastroValenciaApi.mappers.MembershipLevelMapper;
import com.example.gastroValenciaApi.models.MembershipLevelModel;
import com.example.gastroValenciaApi.repositories.MembershipLevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembershipLevelService {

    private final MembershipLevelRepository repository;
    private final MembershipLevelMapper mapper;

    public MembershipLevelService(MembershipLevelRepository repository, MembershipLevelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MembershipLevelDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MembershipLevelDTO> getById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public MembershipLevelDTO save(MembershipLevelDTO dto) {
        MembershipLevelModel entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public String delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Nivel con ID " + id + " no existe.");
        }
        repository.deleteById(id);
        return "Nivel con ID " + id + " eliminado correctamente.";
    }
}