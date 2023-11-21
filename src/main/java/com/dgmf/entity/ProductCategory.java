package com.dgmf.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
        fetch = FetchType.LAZY,
        mappedBy = "productCategory"
    )
    private List<Product> products = new ArrayList<>();

    public ProductCategory() {
    }

    public ProductCategory(
            Long id,
            String categoryName,
            String categoryDescription,
            LocalDateTime dateCreated,
            LocalDateTime lastUpdated,
            List<Product> products
    ) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
