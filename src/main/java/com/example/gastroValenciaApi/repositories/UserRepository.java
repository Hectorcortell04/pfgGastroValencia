package com.example.gastroValenciaApi.repositories;

import com.example.gastroValenciaApi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByFirebaseUid(String firebaseUid);
}
