package com.dgmf.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
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
    @Column(name = "product_description", nullable = false, length = 20)
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
