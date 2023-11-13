package com.dgmf.repository;

import com.dgmf.entity.Product;
import com.dgmf.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void saveProductCategory() {
        // Create a ProductCategory
        ProductCategory productCategory1 = new ProductCategory();
                productCategory1.setCategoryName("Books");
                productCategory1.setCategoryDescription("Books Description");

        // Create Product1
        Product product1 = new Product();
                product1.setProductName("Core Java");
                product1.setProductDescription("Core Java Description");
                product1.setProductPrice(new BigDecimal(1000));
                product1.setImageUrl("image1.png");
                product1.setProductSku("ABCD");
                product1.setActive(true);
                // Assign "productCategory1" to "product1"
                product1.setProductCategory(productCategory1);

        // Assign "product1" to "productCategory1"
        productCategory1.getProducts().add(product1);

        // Create Product2
        Product product2 = new Product();
                product2.setProductName("Spring Framework");
                product2.setProductDescription("Spring Framework Description");
                product2.setProductPrice(new BigDecimal(2000));
                product2.setImageUrl("image2.png");
                product2.setProductSku("ABCDE");
                product2.setActive(true);
                // Assign "productCategory1" to "product1"
                product2.setProductCategory(productCategory1);

        // Assign "product1" to "productCategory1"
        productCategory1.getProducts().add(product2);

        // Save "productCategory1"
        productCategoryRepository.save(productCategory1);
    }
}