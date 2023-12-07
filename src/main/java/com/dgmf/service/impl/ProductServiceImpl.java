package com.dgmf.service.impl;

import com.dgmf.entity.Product;
import com.dgmf.repository.ProductRepository;
import com.dgmf.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> searchProducts(String query) {
        List<Product> products =
                productRepository.searchProductsJPQLSearchQuery(query);

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }
}
