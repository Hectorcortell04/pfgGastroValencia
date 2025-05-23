package com.example.gastroValenciaApi.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.converter.json.GsonBuilderUtils;

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
    private String date;
    @Column(nullable = true, length = 8)
    private String time;
    @Column
    private Double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String duration;

    //No entiendo porque no me coge el @Data en el parámetro "date", por eso fuerzo a que no de error poniendo
    // explicítamente los getters y setters de date (lo mismo que en EventDTO)

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}