package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
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

    @Test
    void findByProductNameOrProductDescriptionMethod() {
        List<Product> products = productRepository
                .findByProductNameOrProductDescription(
                        "Product 1 - Name",
                        "Product 1 - Description"
                );

        products.forEach(product -> System.out.println(product));
    }

    @Test
    void findByProductNameAndProductDescriptionMethod() {
        List<Product> products = productRepository
                .findByProductNameAndProductDescription(
                        "Product 2 - Name",
                        "Product 2 - Description"
                );

        products.forEach(product -> System.out.println(product));
    }

    @Test
    void findDistinctByProductNameMethod() {
        Product product = productRepository
                .findDistinctByProductName("Product 2 - Name");

        System.out.println(product);
    }

    @Test
    void findByProductPriceGreaterThanMethod() {
        List<Product> products = productRepository
                .findByProductPriceGreaterThan(new BigDecimal(400));

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void findByProductPriceLessThanMethod() {
        List<Product> products = productRepository
                .findByProductPriceLessThan(new BigDecimal(500));

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void findByProductNameContainingMethod() {
        List<Product> products = productRepository
                .findByProductNameContaining("product");

        products.forEach(product ->
                        System.out.println(product)
                );
    }

//    @Test
//    void findByProductNameLikeMethod() {
//        List<Product> products = productRepository
//                .findByProductNameLike("product");
//
//        products.forEach(product ->
//                        System.out.println(product.getProductName())
//                );
//    }
}
