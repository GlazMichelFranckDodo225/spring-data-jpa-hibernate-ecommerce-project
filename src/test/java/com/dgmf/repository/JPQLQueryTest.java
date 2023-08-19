package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPQLQueryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByProductNameOrProductDescriptionJPQLIndexParamMethod() {
        Product product = productRepository
                .findByProductNameOrProductDescriptionJPQLIndexParam(
                        "Product 1 - Name",
                        "Product 1 - Description"
                );

        System.out.println(product);
    }

    @Test
    void findByProductNameOrProductDescriptionJPQLNamedParamMethod() {
        Product product = productRepository
                .findByProductNameOrProductDescriptionJPQLNamedParam(
                        "Product 5",
                        "Product 5"
                );

        System.out.println(product);
    }
}
