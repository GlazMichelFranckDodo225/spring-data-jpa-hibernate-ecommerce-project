package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class QueryMethodTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        Product product = productRepository.findByProductName("Product 2");

        System.out.println(product);
    }

    @Test
    void findByIdMethod() {
        // Typically, this "id" comes from the Client
        Long id = 7L;

        // Optional<Product> product = productRepository.findById(id);
        Product product = productRepository.findById(id).get();

        System.out.println(product);
    }
}
