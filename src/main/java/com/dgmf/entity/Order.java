package com.dgmf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_tracking_number", nullable = false)
    private String orderTrackingNumber;
    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "date_created", nullable = false)
    // Hibernate will automatically take the current Timestamp of the JVM
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name = "last_updated")
    // Hibernate will automatically take the current Timestamp of the JVM
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;
    // Default Fetch Type for "OneToMany" is LAZY
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getOrderTotalAmount() {
        BigDecimal amount = new BigDecimal(0.0);

        for(OrderItem orderItem: orderItems) {
            amount = amount.add(orderItem.getPrice());
        }

        return amount;
    }
}
