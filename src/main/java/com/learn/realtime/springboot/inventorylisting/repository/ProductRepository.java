package com.learn.realtime.springboot.inventorylisting.repository;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
