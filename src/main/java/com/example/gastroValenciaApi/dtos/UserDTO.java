package com.example.gastroValenciaApi.dtos;

import com.example.gastroValenciaApi.models.UserModel;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String firebaseUid;
    private LocalDateTime registrationDate;
    private String userImage;
}
