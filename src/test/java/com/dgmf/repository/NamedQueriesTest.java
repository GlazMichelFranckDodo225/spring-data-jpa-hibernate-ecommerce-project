package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class NamedQueriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void namedJPQLQueryIndexParamMethod() {
        List<Product> products = productRepository
                .findByProductPrice(new BigDecimal(700));

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void NamedJPQLQueryNamedParamMethod() {
        Product product = productRepository.findByProductSku("100ABCDEFmsnhg");

        System.out.println(product);
    }
}
