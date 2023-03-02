package com.learn.realtime.springboot.inventorylisting.controller.api;

import com.learn.realtime.springboot.inventorylisting.controller.InventoryListing;
import com.learn.realtime.springboot.inventorylisting.model.Product;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryListingImpl implements InventoryListing {

    @Override
    @GetMapping("/inventory")
    public ResponseEntity<List<Product>> getInventoryList() {
        //return Collections.emptyList();
        LocalDateTime startTime = LocalDateTime.now();

        HttpHeaders headers = new HttpHeaders();

        if (Math.random() > 0.5) {
            headers.set("Error", "Token not found");
            // throw new RuntimeException("Its something went wrong in server");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
        }
        val productList = List.of(
                Product.builder()
                        .productId(23423)
                        .discount(10)
                        .customerFeedback("Very Good")
                        .build()
        );

        LocalDateTime endTime = LocalDateTime.now();
        headers.set("ComputationalTime", String.valueOf(ChronoUnit.MILLIS.between(startTime, endTime)));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(productList);
    }
}
