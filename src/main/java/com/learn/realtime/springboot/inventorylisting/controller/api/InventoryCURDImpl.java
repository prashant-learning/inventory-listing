package com.learn.realtime.springboot.inventorylisting.controller.api;

import com.learn.realtime.springboot.inventorylisting.controller.trait.InventoryAddition;
import com.learn.realtime.springboot.inventorylisting.controller.trait.InventoryListing;
import com.learn.realtime.springboot.inventorylisting.controller.trait.InventoryRemoval;
import com.learn.realtime.springboot.inventorylisting.error.ErrorResponse;
import com.learn.realtime.springboot.inventorylisting.error.InventoryAppErrorStatus;
import com.learn.realtime.springboot.inventorylisting.exception.ProductNotFoundException;
import com.learn.realtime.springboot.inventorylisting.exception.TestUserException;
import com.learn.realtime.springboot.inventorylisting.model.Product;
import com.learn.realtime.springboot.inventorylisting.service.ProductInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class InventoryCURDImpl implements InventoryListing, InventoryAddition, InventoryRemoval {

    @Autowired
    private ProductInventoryService productInventoryService;

    @Override
    @Tag(name = "Listing Inventory")
    @Operation(summary = "Get Inventory list", description = "Gives inventory items in list")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully able to fetch the inventories"),
                    @ApiResponse(responseCode = "404", description = "No items found", content = @Content)
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
    @Operation(summary = "Get Inventory list", description = "Gives inventory items in list")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully able to fetch the inventories"),
                    @ApiResponse(responseCode = "404", description = "No items found", content = @Content)
            }
    )
    @GetMapping("/inventory/product/inputs")
    public ResponseEntity<List<Product>>
    getProductsByManufacturedByAndMarkedBy(@RequestParam(required = true) String manufacturedBy,
                                           @RequestParam(required = true) String markedBy) {

        val response = productInventoryService.getProductsByUserQuery(manufacturedBy, markedBy);

       return ResponseEntity.ok(response);
    }

    @Override
    @Tag(name = "Listing Inventory")
    @Operation(summary = "Get getProductById by Id", description = "Gives inventory items by its ID")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully able to fetch the product"),
                    @ApiResponse(responseCode = "404", description = "No items found", content = @Content)
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
    @Operation(summary = "Get getProductById by Id", description = "Gives inventory items by its ID")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully able to fetch the product"),
                    @ApiResponse(responseCode = "404", description = "No items found", content = @Content)
            }
    )
    @GetMapping("/product/count/{manufacturedBy}")
    public ResponseEntity<Integer> getProductsByManufactured(String manufacturedBy) {
        val count = productInventoryService.getProductCountByManufacturer(manufacturedBy);
       return ResponseEntity.ok(count);
    }

    @Override
    @Tag(name = "Listing Inventory")
    @Operation(summary = "Insert product in Inventory list", description = "")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Successfully inserted product"),
                    @ApiResponse(responseCode = "409", description = "Product already exist",content = @Content),
                    @ApiResponse(responseCode = "406", description = "This is test user no need to insert",content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @PostMapping("/product")
    public ResponseEntity<Void> insertProductInventory(@RequestBody Product product) {

        if(product.getProductId() == 0){
            throw new TestUserException("this is test user");
        }

        val createdProduct = productInventoryService.insertProductInventory(product);

        if(createdProduct != null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Tag(name = "Listing Inventory")
    @Operation(summary = "Delete a product", description = "")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted product"),
                    @ApiResponse(responseCode = "404", description = "Product does not exist",content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",content = @Content)
            }
    )
    @Parameters(
            {
                    @Parameter(name = "token", description = "This is token", in = ParameterIn.HEADER, required = false ),
                    @Parameter(name = "key", description = "This is Key", in = ParameterIn.HEADER, required = false )
            }
    )
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Product> removeProductById(@PathVariable int productId, @RequestHeader(required = false) String token,  @RequestHeader(required = false) String key) {
       //return ResponseEntity.status(200).body(productInventoryService.deleteProductById(productId));
      //  try {
            val productResponse = productInventoryService.deleteProductById(productId);
            return ResponseEntity.status(200).body(productResponse);
   //     }catch (Exception ex){
   //         return ResponseEntity.status(403).build();
   //     }
    }


    @Tag(name = "Listing Inventory")
    @Operation(summary = "Get getProductById by Id", description = "Gives inventory items by its ID")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully able to fetch the product"),
                    @ApiResponse(responseCode = "404", description = "No items found", content = @Content)
            }
    )
    @Parameters(
            {
                    @Parameter(name = "headers", description = "This is headers", in = ParameterIn.HEADER, required = false )
            }
    )
    @GetMapping("/products/{manufacturedBy}")
    public ResponseEntity<Integer> getProductsWithAuth(@PathVariable String manufacturedBy, @RequestHeader Map<String, String> headers) {

        System.out.println( headers.getOrDefault("mouli", "Not passed")); // not a good way to log 

       return ResponseEntity.ok(1);
    }


    @ExceptionHandler({ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException exception){

        ErrorResponse errorResponse  = new ErrorResponse(LocalDateTime.now(), InventoryAppErrorStatus.B001, HttpStatus.NOT_FOUND, exception.getMessage() );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}
