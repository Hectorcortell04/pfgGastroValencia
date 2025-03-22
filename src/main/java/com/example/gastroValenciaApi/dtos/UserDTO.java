package com.example.gastroValenciaApi.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private LocalDateTime registrationDate;
}
