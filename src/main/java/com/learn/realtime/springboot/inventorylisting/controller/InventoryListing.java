package com.learn.realtime.springboot.inventorylisting.controller;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryListing {

    public ResponseEntity<List<Product>> getInventoryList();
}
