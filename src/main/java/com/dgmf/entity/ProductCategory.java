package com.dgmf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;
    @Column(name = "date_created", nullable = false)
    // Hibernate will automatically take the current Timestamp of the JVM
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name = "last_updated")
    // Hibernate will automatically take the current Timestamp of the JVM
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
        fetch = FetchType.LAZY,
        mappedBy = "productCategory"
    )
    private List<Product> products = new ArrayList<>();
}
