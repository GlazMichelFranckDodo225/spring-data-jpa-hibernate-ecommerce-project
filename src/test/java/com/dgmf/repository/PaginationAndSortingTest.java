package com.dgmf.repository;

import com.dgmf.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
        // All Pages
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
                "Total Pages : " + totalPages + " pages" + "\n" +
                "Total elements : " + totalElements + " products" + "\n" +
                "Number of elements per Page : " + numberOfElementsPerPage + " products" + "\n" +
                "Size of the Page : " + size + " products" + "\n" +
                "Is it the First Page ? " + isFirst + "\n" +
                "Is it the Last Page ? " + isLast + "\n"
        );
    }

    @Test
    void sortingBySingleFieldMethod() {
        // Typically, these two fields below come from the Client
        String sortBy = "productPrice"; // Entity Product field name
        String sortDir = "desc";

        // Sort Object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // "org.springframework.data.domain.Sort"
        // Sort Prices By DESC
        List<Product> products = productRepository
                // .findAll(Sort.by(sortBy).descending());
                // .findAll(Sort.by(sortBy)); // ".ascending()" By Default
                .findAll(sort); // ".ascending()" By Default

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void sortingByMultipleFieldsMethod() {
        // Typically, these three fields below come from the Client
        String sortByProductName = "productName"; // Entity Product field name
        String sortByProductDescription = "productDescription"; // Entity Product field name
        String sortDir = "desc";

        // Three Sort Objects
        Sort sortObjectForProductName = sortDir
                .equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByProductName).ascending() :
                Sort.by(sortByProductName).descending();

        Sort sortObjectForProductDescription = sortDir
                .equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByProductDescription).ascending() :
                Sort.by(sortByProductDescription).descending();

        Sort groupBySort = sortObjectForProductName
                .and(sortObjectForProductDescription);

        List<Product> products = productRepository.findAll(groupBySort);

        products.forEach(product ->
                        System.out.println(product)
                );
    }

    @Test
    void paginationAndSortingTogetherMethod() {
        // Typically, these fields below come from the Client
        // Fields for Sorting
        String sortByProductPrice = "productPrice"; // Entity Product field name
        String sortDir = "desc";
        // Fields for Pagination
        int pageNo = 0; // First page
        int pageSize = 5; // Five Records per page

        // Sort Object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByProductPrice).ascending() :
                Sort.by(sortByProductPrice).descending();

        // Pageable Object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // All Pages
        Page<Product> page = productRepository.findAll(pageable);
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
                "Total Pages : " + totalPages + " pages" + "\n" +
                "Total elements : " + totalElements + " products" + "\n" +
                "Number of elements per Page : " + numberOfElementsPerPage + " products" + "\n" +
                "Size of the Page : " + size + " products" + "\n" +
                "Order By : " + sortDir.toUpperCase() + "\n" +
                "Is it the First Page ? " + isFirst + "\n" +
                "Is it the Last Page ? " + isLast + "\n"
        );
    }
}
