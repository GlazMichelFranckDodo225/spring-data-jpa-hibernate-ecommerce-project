package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

// Load full "ApplicationContext" of the Application in order
// to be able to inject all the Spring Beans
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void saveMethod() {
        // Create Product
        Product productA = new Product();
                productA.setProductName("Product A");
                productA.setProductDescription("Product A");
                productA.setProductSku("mdl253qfk");
                productA.setProductPrice(new BigDecimal(13226));
                productA.setActive(true);
                productA.setImageUrl("productA.png");
                productA.setProductCategory(productCategoryRepository.findById(1L).get());

        // Save Product
        Product savedProduct = productRepository.save(productA);

        // Display Product Information
        System.out.println(savedProduct.getId());
        // System.out.println(savedProduct); // fetch.FetchType.LAZY
    }

    @Test
    void updateUsingSaveMethod() {
        // Find or Retrieve an Entity by Id
        Long id = 7L;
        Product product = productRepository.findById(id).get();

        // Update Entity Information
        product.setProductName("Product 1 - Name");
        product.setProductDescription("Product 1 - Description");

        // Save Updated Entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod() {
        Long id = 1L; // Typically, this id comes from the Client
        Product product = productRepository.findById(id).get();

        System.out.println(product);
    }

    @Test
    void saveAllMethod() {
        // Create 2 Products
        Product productB = new Product();
        productB.setProductName("Product B");
        productB.setProductDescription("Product B");
        productB.setProductSku("mdnx3q5s69");
        productB.setProductPrice(new BigDecimal(32145));
        productB.setActive(true);
        productB.setImageUrl("productB.png");
        productB.setProductCategory(productCategoryRepository.findById(1L).get());

        Product productC = new Product();
        productC.setProductName("Product C");
        productC.setProductDescription("Product C");
        productC.setProductSku("nclod2d1c6q");
        productC.setProductPrice(new BigDecimal(325831));
        productC.setActive(true);
        productC.setImageUrl("productC.png");
        productC.setProductCategory(productCategoryRepository.findById(1L).get());

        Product productD = new Product();
        productD.setProductName("Product D");
        productD.setProductDescription("Product D");
        productD.setProductSku("ytmq626cqs");
        productD.setProductPrice(new BigDecimal(32369));
        productD.setActive(true);
        productD.setImageUrl("productD.png");
        productD.setProductCategory(productCategoryRepository.findById(1L).get());



        // Save Multiple Products (product2 and product3)
        productRepository.saveAll(List.of(productB, productC, productD));
    }

    @Test
    void findAllMethod() {
        List<Product> products = productRepository.findAll();

        products.forEach(product ->
                System.out.println(product.getProductName()));
    }

    @Test
    void deleteByIdMethod() {
        // Basically, this "id" comes from the Client
        Long id = 1L;

        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod() {
        // Find an Entity by "id"
        // Basically, this "id" comes from the Client
        Long id = 2L;
        Product product = productRepository.findById(id).get();

        // Delete the retrieval Entity
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod1() {
        // Delete all Entities
        productRepository.deleteAll();
    }

    @Test
    void deleteAllMethod2() {
        // Basically, these "id" comes from the Client
        Long id1 = 5L;
        Long id2 = 6L;

        // Find the Entities by "id"
        Product product1 = productRepository.findById(id1).get();
        Product product2 = productRepository.findById(id2).get();

        // Delete the specifies Entities
        productRepository.deleteAll(List.of(product1, product2));
    }

    @Test
    void countMethod() {
        Long count = productRepository.count();

        System.out.println(count);
    }

    @Test
    void existsByIdMethod() {
        // Typically, this "id" comes from the Client
        // Long id = 1L; // This "id" does not exist into the DB
        Long id = 8L; // This "id" exists into the DB

        boolean result = productRepository.existsById(id);
        System.out.println(result);
    }
}