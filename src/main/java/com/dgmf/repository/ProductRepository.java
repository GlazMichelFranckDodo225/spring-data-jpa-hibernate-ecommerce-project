package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

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
}


