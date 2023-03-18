package com.learn.realtime.springboot.inventorylisting.controller.trait;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.http.ResponseEntity;

public interface InventoryRemoval {

    public ResponseEntity<Product> removeProductById(int productId, String token, String key);
}
