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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    // Foreign Key ==> Column "billing_address_id" of "Order" Entity
    // Primary Key ==> Column "id" of "Address" Entity
    /*
    @OneToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.REMOVE
    })
    */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;
}
