package com.example.gastroValenciaApi.dtos;

import com.example.gastroValenciaApi.enums.EventType;
import lombok.Data;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private EventType category;
    private String location;
    private String date;
    private String time;
    private Double price;
    private String description;
    private String duration;
    private String eventImage;
    private boolean liked;
    private String eventWeb;

}