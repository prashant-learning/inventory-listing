package com.learn.realtime.springboot.inventorylisting.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Product {

    private int productId;
    private String productName;
    private String manufacturedBy;
    private String markedBy;
    private double price;
    private int discount;
    private String soldBY;
    private String feature;
    private String description;
    private String specification;
    private String imageUrl;
    private double reviews;
    private String customerFeedback;
}
