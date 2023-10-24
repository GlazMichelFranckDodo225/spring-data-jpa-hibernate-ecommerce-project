package com.dgmf.repository;

import com.dgmf.entity.Address;
import com.dgmf.entity.Order;
import com.dgmf.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyMappingTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    // Save Order (Parent Entity) along with
    // also Save it's Order Items (Child Entity)
    @Test
    void saveOrderMethod() {
        // Create Order
        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus("In Progress");

        // Create Order Item 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1.setPrice(
            productRepository.findById(1L).get()
                    .getProductPrice()
                    .multiply(new BigDecimal(2))
        );
        orderItem1.setImageUrl("image1.png");

        // Assign orderItem1 to order
        order.getOrderItems().add(orderItem1);

        // Create Order Item 2
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2.setPrice(
            productRepository.findById(2L).get()
                    .getProductPrice()
                    .multiply(new BigDecimal(3))
        );
        orderItem2.setImageUrl("image2.png");

        // Assign orderItem1 to order
        order.getOrderItems().add(orderItem2);

        // Order Total Price and Quantity
        order.setTotalPrice(order.getOrderTotalAmount());
        order.setTotalQuantity(2);

        // Create Address
        Address address = new Address();
        address.setCity("Paris");
        address.setStreet("25, Allée de la Bavière");
        address.setState("France");
        address.setCountry("France");
        address.setZipCode("91452");

        // Assign Billing Address to order
        order.setBillingAddress(address);

        // Save Order
        orderRepository.save(order);
    }
}
