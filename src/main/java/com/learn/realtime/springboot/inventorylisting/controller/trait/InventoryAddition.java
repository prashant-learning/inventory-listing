package com.learn.realtime.springboot.inventorylisting.controller.trait;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.http.ResponseEntity;

public interface InventoryAddition {


    public ResponseEntity<Void> insertProductInventory(Product product);
}
