package com.BackendChallenge.TechTrendEmporium.entity;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table (name = "user") // Table name: user
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique id, to identify each user

    private String name; // name that would be a string

    @Column(unique = true)
    private String username; // username that would be a string (should be unique)

    @Column(unique = true)
    private String email; // email that should be a string (should be unique)

    private String password; // password

    private String role; // role that should be a string

    private boolean logged; // logged that should be a boolean
}

