package com.learn.realtime.springboot.inventorylisting.service;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import com.learn.realtime.springboot.inventorylisting.repository.ProductRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductInventoryService {

    @Autowired
    private ProductRepository productRepository;

    public Product insertProductInventory(Product product){

        return productRepository.save(product);

    }

    public List<Product> productsListing(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;

//        val productIterator = productRepository.findAll().iterator();
//
//        while (productIterator.hasNext()){
//            products.add(productIterator.next());
//        }

      //  return products;
    }


    public Optional<Product> productById(int productId){

        return productRepository.findById(productId);
    }

    public List<Product> getProductsByUserQuery(String manufacturedBy, String markedBy) {
        return productRepository.findByManufacturedByAndMarkedBy(manufacturedBy, markedBy);
    }

    public int getProductCountByManufacturer(String manufacturedBy){
        return productRepository.getProductCountByManufacturer(manufacturedBy);
    }

    public void deleteProductById(int productId){
         productRepository.deleteById(productId);
    }
}
