package com.example.gastroValenciaApi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "membership_levels")
public class MembershipLevelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
}
