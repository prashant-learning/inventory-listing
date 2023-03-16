package com.learn.realtime.springboot.inventorylisting.controller.trait;

import org.springframework.http.ResponseEntity;

public interface InventoryRemoval {

    public ResponseEntity<Object> removeProductById(int productId, String token);
}
