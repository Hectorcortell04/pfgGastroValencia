package com.example.gastroValenciaApi.dtos;

import lombok.Data;

@Data
public class EventDTO {
    private Integer id;
    private String name;
    private String category;
    private String location;
    private String date;
    private String time;
    private Double price;
    private String description;
    private String duration;
    private String eventImage;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}