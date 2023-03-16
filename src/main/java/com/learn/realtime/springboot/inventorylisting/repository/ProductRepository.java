package com.learn.realtime.springboot.inventorylisting.repository;

import com.learn.realtime.springboot.inventorylisting.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    // Syntatical sugar of JPA

    //SELECT * FROM uhgsmedb.product where manufactured_by='ABC' and marked_by='XYZ';
    //findByManufacturedByAndMarkedBy
    public List<Product> findByManufacturedByAndMarkedBy(String manufacturedBy, String markedBy);
    public List<Product> findByProductName(String productName);

    @Query(value = "SELECT count(*) FROM product where manufactured_by = :manufacturedBy", nativeQuery = true)
    public int getProductCountByManufacturer(String manufacturedBy);
}
