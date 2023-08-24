package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void pagination() {
        // Typically, "pageNo" and "pageSize" come from the Client
        // Page number ==> Spring Data JPA starts
        // the count of Pages to 0
        int pageNo = 0;
        // Number of Products displayed per Page ==> Here,
        // 5 Products per Page
        int pageSize = 5;

        // Creates a "pageable" Object
        // from ==> "org.springframework.data.domain"
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        // A Page of Products
        Page<Product> page = productRepository.findAll(pageable);
        // A List of Products from "page" Object
        List<Product> products = page.getContent();
        // Displays the Products (5 products per page)
        products.forEach(product ->
                        System.out.println(product)
                );

        // Information about the Page
        // Total Pages
        int totalPages = page.getTotalPages();
        // Total elements
        Long totalElements = page.getTotalElements();
        // Number of elements per Page
        int numberOfElementsPerPage = page.getNumberOfElements();
        // Size of the Page
        int size = page.getSize();
        // Is it the First Page ?
        boolean isFirst = page.isFirst();
        // Is it the Last Page ?
        boolean isLast = page.isLast();

        // Displays Information about the Page
        System.out.println(
                "Total Pages : " + totalPages + "\n" +
                "Total elements : " + totalElements + "\n" +
                "Number of elements per Page : " + numberOfElementsPerPage + "\n" +
                "Size of the Page : " + size + "\n" +
                "Is it the First Page ? " + isFirst + "\n" +
                "Is it the Last Page ? " + isLast + "\n"
        );

    }
}
