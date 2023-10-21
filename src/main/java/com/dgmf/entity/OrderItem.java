package com.dgmf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int quantity;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
