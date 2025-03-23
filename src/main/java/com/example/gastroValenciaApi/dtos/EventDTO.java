package com.example.gastroValenciaApi.dtos;

import lombok.Data;

@Data
public class EventDTO {
    private int id;
    private String name;
    private String category;
    private String location;
    private String date;
    private Double price;
    private String description;
    private String duration;
}
