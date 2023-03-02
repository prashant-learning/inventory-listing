package com.learn.realtime.springboot.inventorylisting.controller.api;

import com.learn.realtime.springboot.inventorylisting.controller.InventoryListing;
import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryListingImpl implements InventoryListing {

    @Override
    @GetMapping("/inventory")
    public List<Product> getInventoryList() {
        //return Collections.emptyList();
        return  List.of(Product.builder().productId(23423).discount(10).customerFeedback("Very Good").build());
    }
}
