package com.dgmf.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.NamedQuery;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@NamedQueries(
        {
                @NamedQuery(
                        name = "Product.findAllOrderByProductNameDesc",
                        query = "SELECT p FROM Product p ORDER BY " +
                                "p.productName DESC "
                ),
                @NamedQuery(
                        name = "Product.findByProductPrice",
                        query = "SELECT p FROM Product p WHERE " +
                                "p.productPrice = ?1"
                ),
                @NamedQuery(
                        name = "Product.findByProductSku",
                        query = "SELECT p FROM Product p WHERE " +
                                "p.productSku = :productSku"
                )
        }
)

@NamedNativeQueries(
        {
                // "Named" Parameter
                @NamedNativeQuery(
                        name = "Product.findByProductDescription",
                        query = "SELECT * FROM products p WHERE " +
                                "p.product_description = :product_description",
                        resultClass = Product.class
                ),
                // "Index or "Position" Parameter
                @NamedNativeQuery(
                        name = "Product.findAllOrderByProductNameAsc",
                        query = "SELECT * FROM products p ORDER BY " +
                                "p.product_name ASC",
                        resultClass = Product.class
                )
        }
)
@Table(
        name = "products",
        schema = "ecommerce",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "productSku_unique", // Entity Attribute name
                        columnNames = "product_stock_keeping_unit" // DB Column name
                ),
                @UniqueConstraint(
                        name = "productName_unique", // Entity Attribute name
                        columnNames = "product_name" // DB Column name
                )
        }
)
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )
    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "product_stock_keeping_unit", nullable = false, length = 20)
    private String productSku;
    @Column(name = "product_name", nullable = false, length = 20)
    private String productName;
    @Column(name = "product_description", nullable = false)
    private String productDescription;
    @Column(name = "product_price", nullable = false)
    @Positive
    private BigDecimal productPrice;
    private boolean isActive;
    @Column(name = "image_url", nullable = false, length = 50)
    private String imageUrl;
    @Column(name = "date_created", nullable = false)
    // Hibernate will automatically take the current Timestamp of the JVM
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name = "last_updated")
    // Hibernate will automatically take the current Timestamp of the JVM
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
