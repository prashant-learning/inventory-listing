package com.learn.realtime.springboot.inventorylisting.controller.trait;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryListing {

    public ResponseEntity<List<Product>> getInventoryList();
    public ResponseEntity<List<Product>> getProductsByManufacturedByAndMarkedBy(String manufacturedBy, String markedBy);
    public ResponseEntity<Product> getProductById(int id);
    public ResponseEntity<Integer> getProductsByManufactured(String manufacturedBy);
}
