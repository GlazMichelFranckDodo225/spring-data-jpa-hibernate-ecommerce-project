package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodsTest {
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

    @Test
    void findByProductPriceBetweenMethod() {
        List<Product> products =
                productRepository.findByProductPriceBetween(
                        new BigDecimal(300),
                        new BigDecimal(600)
                );

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void findByDateCreatedBetweenMethod() {
        // Start Date
        LocalDateTime startDate = LocalDateTime.of(
                2023, 8, 15, 19, 1, 42);
        // End Date
        LocalDateTime endDate = LocalDateTime.of(
                2023, 8, 15, 19, 4, 58);

        List<Product> products = productRepository.findByDateCreatedBetween(
            startDate,
            endDate
        );

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void findByProductNameInMethode() {
        List<Product> products = productRepository
                .findByProductNameIn(List.of(
                        "Product 5",
                        "Product 6",
                        "Product 7"
                )
        );

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void findFirst2ByOrderByProductNameAscMethod() {
        List<Product> products = productRepository
                .findFirst2ByOrderByProductNameAsc();

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void findTop3ByOrderByProductPriceDesc() {
        List<Product> products = productRepository
                .findTop3ByOrderByProductPriceDesc();

        products.forEach(product ->
                        System.out.println(product)
                );
    }
}
