package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /*
    * Returns the found Product entry by using its name
    * as search criteria. If no Product entry is found,
    * this method returns "null"
    */
    Product findByProductName(String name);
    /* Returns an Optional which contains the found Product entry
    * by using its "id" as search criteria. If no Product entry is found,
    * this method returns an empty Optional
    */
    Optional<Product> findById(Long id);
    /*
    * Returns the found list of Product entries whose "productName" OR
    * "productDescription" are given as a Method parameter. If no Product
    * entries is found, this Method returns an empty list.
    */
    List<Product> findByProductNameOrProductDescription(
            String productName,
            String productDescription
    );

    /*
    * Returns the found list of Product entries whose "productName" AND
    * "productDescription" are given as a Method parameter. If no Product
    * entries is found, this Method returns an empty list.
    */
    List<Product> findByProductNameAndProductDescription(
            String productName,
            String productDescription
    );

    /*
    * Returns the Distinct Product entry whose "productName" is
    * given as a Method parameter. If no Product entry is
    * found, this Method returns "null"
    */
    Product findDistinctByProductName(String productName);
    /*
    * Returns the Products whose price are Greater Than the
    * given price as Method parameter
    */
    List<Product> findByProductPriceGreaterThan(BigDecimal productPrice);
    /*
     * Returns the Products whose price are Less Than the
     * given price as Method parameter
    */
    List<Product> findByProductPriceLessThan(BigDecimal productPrice);
    /*
     * Returns the filtered Product records that match the
     * given text as Method parameter
    */
    List<Product> findByProductNameContaining(String text);
    /*
     * Returns Products based on SQL "Like" condition
    */
    // List<Product> findByProductNameLike(String text); // "%text%"
    /*
     * Returns Products based on a Price range whose prices
     * are Between "startPrice" and "endPrice" given as Method
     * parameter ==> Lower terminal and Upper terminal included
    */
    List<Product> findByProductPriceBetween(
            BigDecimal startPrice,
            BigDecimal endPrice)
    ;
    /*
     * Returns Products based on a Date range whose dates
     * are Between "startDate" and "endDate" given as Method
     * parameter ==> Lower terminal and Upper terminal included
    */
    List<Product> findByDateCreatedBetween(
            LocalDateTime startDate,
            LocalDateTime endDate
    );
    /*
     * Returns Products based on a list of Products given
     * as Method parameter
    */
    List<Product> findByProductNameIn(List<String> productsNames);
    /*
     * Returns the First 2 Products between all Products Order
     * By "ProductName" (ASC - Default)
    */
    List<Product> findFirst2ByOrderByProductNameAsc();
    /*
     * Returns the Top 3 Products between all Products Order
     * By "ProductPrice" (DESC)
    */
    List<Product> findTop3ByOrderByProductPriceDesc();
    /*
     * Defines JPQL Query using @Query annotation with
     * "Index" or "Position" parameters.
     * We don't have to follow "Naming Convention" because
     * or @Query annotation
    */
    @Query("SELECT p FROM Product p WHERE p.productName = ?1 " +
            "OR p.productDescription = ?2")
    Product findByProductNameOrProductDescriptionJPQLIndexParam(
            String productName, String productDescription
    );
    /*
     * Defines JPQL Query using @Query annotation with
     * "Named" parameters.
     * We don't have to follow "Naming Convention" because
     * or @Query annotation
     */
    @Query("SELECT p FROM Product p WHERE p.productName = :productName " +
            "OR p.productDescription = :productDescription")
    Product findByProductNameOrProductDescriptionJPQLNamedParam(
            @Param("productName") String productName,
            @Param("productDescription") String productDescription
    );
}


