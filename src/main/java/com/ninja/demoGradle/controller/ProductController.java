package com.ninja.demoGradle.controller;

import com.ninja.demoGradle.model.Product;
import com.ninja.demoGradle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product prod= service.getProductById(id);
        if(prod!=null){
            return new ResponseEntity<>(prod, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try {
            service.addProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        Product product1= null;
        try {
            product1=service.updateProduct(product);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        if (product1!=null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Failed to Update", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product= service.getProductById(id);
        if(product!=null){
            try {
                service.deleteProductById(id);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/name")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name){
        String lowerCase= name.toLowerCase();
        System.out.println("Searching with: "+ lowerCase);
        List<Product> prod= service.searchProduct(lowerCase);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }
}
