package com.example.gastroValenciaApi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "events")
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 100)
    private String category;
    @Column(nullable = false, length = 250)
    private String location;
    @Column(nullable = false)
    private LocalDate date;
    @Column
    private Double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String duration;
}
