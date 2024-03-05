package com.BackendChallenge.TechTrendEmporium.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "product") // Table name: product
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id to uniquely identify the product

    private String title; // title should be a string

    private String price; // price should be a string

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; // category_id: connect with the category table

    private String description; // description should be a string

    private String image; // image should be a string

    private Integer inventoryId; // Inventory_id: an integer for inventory
}

