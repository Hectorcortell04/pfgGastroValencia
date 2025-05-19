package com.example.gastroValenciaApi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "event_likes")
public class EventLikeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private EventModel event;


}