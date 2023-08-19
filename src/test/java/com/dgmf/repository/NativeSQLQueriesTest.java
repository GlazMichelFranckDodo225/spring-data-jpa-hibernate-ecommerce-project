package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NativeSQLQueriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByProductNameOrProductDescriptionSQLIndexParamMethod() {
        Product product = productRepository
                .findByProductNameOrProductDescriptionSQLIndexParam(
                        "Product 6",
                        "Product 6"
                );

        System.out.println(product);
    }
}
