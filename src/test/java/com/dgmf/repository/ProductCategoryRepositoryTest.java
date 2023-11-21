package com.dgmf.repository;

import com.dgmf.entity.Product;
import com.dgmf.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void saveProductCategory() {
        // Create a ProductCategory
        ProductCategory productCategory1 = new ProductCategory();
                productCategory1.setCategoryName("Blouses");
                productCategory1.setCategoryDescription("Blouses Description");

        // Create Product1
        Product product1 = new Product();
                product1.setProductName("Alexia écrue");
                product1.setProductDescription("Alexia écrue Description");
                product1.setProductPrice(new BigDecimal(3000));
                product1.setImageUrl("blouse-alexia-ecrue.png");
                product1.setProductSku("2Bhd36");
                product1.setActive(true);
                // Assign "productCategory1" to "product1"
                product1.setProductCategory(productCategory1);

        // Assign "product1" to "productCategory1"
        productCategory1.getProducts().add(product1);

        // Create Product2
        Product product2 = new Product();
                product2.setProductName("Juna en dentelle noire");
                product2.setProductDescription("Juna en dentelle noire Description");
                product2.setProductPrice(new BigDecimal(8000));
                product2.setImageUrl("blouse-juna-en-dentelle-noire.png");
                product2.setProductSku("mc258DF");
                product2.setActive(true);
                // Assign "productCategory1" to "product1"
                product2.setProductCategory(productCategory1);

        // Assign "product1" to "productCategory1"
        productCategory1.getProducts().add(product2);

        // Save "productCategory1"
        productCategoryRepository.save(productCategory1);
    }

    @Test
    @Transactional
    void fetchProductCategory() {
        // Attribute "products" in the "ProductCategory" Class is
        // now on "fetch = FetchType.LAZY" ==> This "productCategory"
        // below contains any Information for any associated Product.
        // It contains only "ProductCategory" Information
        // Get only ProductCategory Information because
        // of "fetch = FetchType.LAZY"
        System.out.println("==== Get only ProductCategory Information because " +
                "of 'fetch = FetchType.LAZY' ====");
        ProductCategory productCategory =
                productCategoryRepository.findById(1L).get();
        System.out.println(productCategory);

        // Get associated Products on demand/request
        System.out.println("==== Get associated Products on demand/request ====");
        System.out.println(productCategory.getProducts());
    }
}