package com.example.gastroValenciaApi.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DiscountDTO {
    private Long id;
    private Long userId;
    private Long membershipLevelId;
    private List<String> discounts;
}