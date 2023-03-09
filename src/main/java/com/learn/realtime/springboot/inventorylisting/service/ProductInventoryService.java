package com.learn.realtime.springboot.inventorylisting.service;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import com.learn.realtime.springboot.inventorylisting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryService {

    @Autowired
    private ProductRepository productRepository;

    public Product insertProductInventory(Product product){

        return productRepository.save(product);

    }

}
