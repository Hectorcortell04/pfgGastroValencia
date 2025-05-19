package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.MembershipLevelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipLevelRepository extends JpaRepository<MembershipLevelModel, Long> {
}