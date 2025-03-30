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


    //No entiendo porque no me coge el @Data en el parámetro "date", por eso fuerzo a que no de error poniendo
    // explicítamente los getters y setters de date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
