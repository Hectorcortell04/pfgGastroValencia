package com.example.gastroValenciaApi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum RestaurantType {
    ITALIANO("Italiano"),
    ASIATICO("Asiático"),
    JAPONES("Japonés"),
    ESPAÑOL("Español"),
    MEXICANO("Mexicano"),
    VEGETARIANO("Vegetariano"),
    AMERICANO("Americano");

    private final String label;

    RestaurantType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static RestaurantType fromLabel(String label) {
        return Arrays.stream(values())
                .filter(e -> e.label.equalsIgnoreCase(label.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de restaurante no válido: " + label));
    }
}
