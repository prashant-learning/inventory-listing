package com.learn.realtime.springboot.inventorylisting.service;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryService {


    public void insertProductInventory(Product product){

        System.out.println("we are inside service");
        System.out.println(product);

    }

}
