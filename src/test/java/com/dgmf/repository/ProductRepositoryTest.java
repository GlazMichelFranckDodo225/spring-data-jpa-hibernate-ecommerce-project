package com.dgmf.repository;

import com.dgmf.entity.Product;
import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

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

    @Test
    void findByIdMethod() {
        Long id = 1L; // Typically, this id comes from the Client
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod() {
        // Create 2 Products
        Product product2 = Product.builder()
                .productName("Product 2")
                .productDescription("Product 2")
                .productSku("100ABCD")
                .productPrice(new BigDecimal(200))
                .isActive(true)
                .imageUrl("product2.png")
                .build();

        Product product3 = Product.builder()
                .productName("Product 3")
                .productDescription("Product 3")
                .productSku("100ABCDE")
                .productPrice(new BigDecimal(300))
                .isActive(true)
                .imageUrl("product3.png")
                .build();

        // Save Multiple Products (product2 and product3)
        productRepository.saveAll(List.of(product2, product3));
    }

    @Test
    void findAllMethod() {
        List<Product> products = productRepository.findAll();

        products.forEach(product -> 
                System.out.println(product.getProductName()));
    }
}