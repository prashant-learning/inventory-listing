package com.learn.realtime.springboot.inventorylisting.service;

import com.learn.realtime.springboot.inventorylisting.exception.DuplicateProductInsertionError;
import com.learn.realtime.springboot.inventorylisting.exception.ProductNotFoundException;
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

        if (productRepository.findById(product.getProductId()).isPresent()){
            throw new DuplicateProductInsertionError("Product Already exist");
        }
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

    /**
     *
     *  1. Simply delete it and always send 200 response either it present or not present we dont care
     *  2. First get and verify if item present, if present then delete or else return different(404) status code
     *  3. First get and if item is present then save it to archive table and delete from original table
     */
    public Product deleteProductById(int productId){
        Optional<Product> mayBeproduct = productRepository.findById(productId);
        if (mayBeproduct.isPresent()){
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException("Product is not present in our inventory");
        }
        return mayBeproduct.get();
    }
}
