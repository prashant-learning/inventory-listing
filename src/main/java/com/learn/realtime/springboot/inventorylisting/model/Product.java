package com.learn.realtime.springboot.inventorylisting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


/**
 *
 *    create a table in sql database and share the create query
 *
 */
@Entity
@Table(name = "product")
@ToString
@Setter
@Getter
public class Product {

    @Id
    private int productId;

    private String productName;
    private String manufacturedBy;
    private String markedBy;
    private double price;
    private int discount;
    private String soldBy;
    private String feature;
    private String description;
    private String specification;
    private String imageUrl;
    private double reviews;
    private String customerFeedback;

    public Product() {
    }
}
