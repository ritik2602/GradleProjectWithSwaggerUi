package com.ninja.demoGradle.service;

import com.ninja.demoGradle.model.Product;
import com.ninja.demoGradle.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;


    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        repo.save(product);
    }

    public Product updateProduct(Product product) {
        if(repo.existsById(product.getId()))
            return repo.save(product);
        else
            return null;
    }

    public void deleteProductById(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProduct(String name) {
        List<Product> prod=repo.searchProductByName(name);
        System.out.println("Found "+ prod.size()+" products");
        return prod;
    }
}
