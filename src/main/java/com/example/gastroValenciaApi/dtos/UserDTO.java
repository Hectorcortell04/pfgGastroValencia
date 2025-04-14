package com.example.gastroValenciaApi.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email; // Este campo se sobrescribe desde el token
    private String firebaseUid;
    private LocalDateTime registrationDate;
}
