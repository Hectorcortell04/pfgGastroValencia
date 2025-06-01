package com.example.gastroValenciaApi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum EventType {
    MUSICA("Música"),
    ARTE("Arte"),
    DEPORTES("Deportes"),
    EXPOSICION("Exposición"),
    TRADICIONAL("Tradicional"),
    FERIA("Feria");

    private final String label;

    EventType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static EventType fromLabel(String label) {
        return Arrays.stream(values())
                .filter(e -> e.label.equalsIgnoreCase(label.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Categoría no válida: " + label));
    }
}
