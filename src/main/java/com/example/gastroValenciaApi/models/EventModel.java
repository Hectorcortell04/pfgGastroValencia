package com.example.gastroValenciaApi.models;

import com.example.gastroValenciaApi.enums.EventType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "events")
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EventType category;
    @Column(nullable = false, length = 250)
    private String location;
    @Setter
    @Getter
    @Column(nullable = false)
    private String date;
    @Column(nullable = true, length = 8)
    private String time;
    @Column
    private Double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String duration;
    @Column(name = "event_image", length = 255)
    private String eventImage;
    @Column(name = "event_web", length = 255)
    private String eventWeb;


}