package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.DiscountModel;
import com.example.gastroValenciaApi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<DiscountModel, Long> {
    Optional<DiscountModel> findByUserId(Long userId);


}
