package com.ninja.demoGradle.repo;

import com.ninja.demoGradle.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE CONCAT('%',:name,'%')")
    List<Product> searchProductByName(@Param("name") String name);
}
