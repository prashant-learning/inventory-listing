package com.learn.realtime.springboot.inventorylisting.controller.api;

import com.learn.realtime.springboot.inventorylisting.controller.trait.InventoryAddition;
import com.learn.realtime.springboot.inventorylisting.controller.trait.InventoryListing;
import com.learn.realtime.springboot.inventorylisting.model.Product;
import com.learn.realtime.springboot.inventorylisting.service.ProductInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class InventoryCURDImpl implements InventoryListing, InventoryAddition {

    @Autowired
    private ProductInventoryService productInventoryService;

    @Override
    @Tag(name = "Listing Inventory")
    @Operation(summary = "Get Inventory list", description = "Gives inventory items in list")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully able to fetch the inventories"),
                    @ApiResponse(responseCode = "404", description = "No items found")
            }
    )
    @GetMapping("/inventory")
    public ResponseEntity<List<Product>> getInventoryList() {
        //return Collections.emptyList();
        LocalDateTime startTime = LocalDateTime.now();

        HttpHeaders headers = new HttpHeaders();

        List<Product> products = productInventoryService.productsListing();

        LocalDateTime endTime = LocalDateTime.now();
        headers.set("ComputationalTime", String.valueOf(ChronoUnit.MILLIS.between(startTime, endTime)));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(products);
    }

    @Override
    @Tag(name = "Listing Inventory")
    @Operation(summary = "Get getProductById by Id", description = "Gives inventory items by its ID")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully able to fetch the product"),
                    @ApiResponse(responseCode = "404", description = "No items found")
            }
    )
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        //return Collections.emptyList();
        LocalDateTime startTime = LocalDateTime.now();

        HttpHeaders headers = new HttpHeaders();

        Optional<Product> mayBeProducts = productInventoryService.productById(id);

        LocalDateTime endTime = LocalDateTime.now();
        headers.set("ComputationalTime", String.valueOf(ChronoUnit.MILLIS.between(startTime, endTime)));

        if(mayBeProducts.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(mayBeProducts.get());
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @Tag(name = "Listing Inventory")
    @Operation(summary = "Insert product in Inventory list", description = "")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Successfully inserted product"),
                    @ApiResponse(responseCode = "409", description = "Product already exist"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/product")
    public ResponseEntity<Void> insertProductInventory(@RequestBody Product product) {


        val createdProduct = productInventoryService.insertProductInventory(product);

        if(createdProduct != null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
