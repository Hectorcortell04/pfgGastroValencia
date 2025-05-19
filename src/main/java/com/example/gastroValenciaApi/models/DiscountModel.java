package com.example.gastroValenciaApi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "discounts")
public class DiscountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "membership_level_id")
    private MembershipLevelModel membershipLevel;
}
