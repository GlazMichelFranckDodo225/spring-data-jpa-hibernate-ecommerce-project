package com.dgmf.repository;

import com.dgmf.entity.Product;
import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

// Load full "ApplicationContext" of the Application in order
// to be able to inject all the Spring Beans
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod() {
        // Create Product
        Product product = Product.builder()
                .productName("Product 1")
                .productDescription("Product 1")
                .productSku("100ABC")
                .productPrice(new BigDecimal(100))
                .isActive(true)
                .imageUrl("product1.png")
                .build();
        // Save Product
        Product savedProduct = productRepository.save(product);
        // Display Product Information
        System.out.println(savedProduct.getId());
        System.out.println(savedProduct);
    }

    @Test
    void updateUsingSaveMethod() {
        // Find or Retrieve an Entity by Id
        Long id = 1L;
        Product product = productRepository.findById(id).get();

        // Update Entity Information
        product.setProductName("Updated Product 1");
        product.setProductDescription("Updated Product 1 Description");

        // Save Updated Entity
        productRepository.save(product);
    }
}