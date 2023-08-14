package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

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
}


