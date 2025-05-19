package com.example.gastroValenciaApi.dtos;

import lombok.Data;

@Data
public class DiscountDTO {
    private Long id;
    private Long userId;
    private Long membershipLevelId;
}