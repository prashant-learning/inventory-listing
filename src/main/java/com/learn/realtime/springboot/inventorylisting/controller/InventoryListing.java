package com.learn.realtime.springboot.inventorylisting.controller;

import com.learn.realtime.springboot.inventorylisting.model.Product;

import java.util.List;

public interface InventoryListing {

    public List<Product> getInventoryList();
}
