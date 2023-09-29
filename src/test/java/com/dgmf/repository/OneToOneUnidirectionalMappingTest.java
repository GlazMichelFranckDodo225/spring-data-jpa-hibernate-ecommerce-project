package com.dgmf.repository;

import com.dgmf.entity.Address;
import com.dgmf.entity.Order;
import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrderMethod() {
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("Paris");
        address.setStreet("25, Allée de la Bavière");
        address.setState("France");
        address.setCountry("France");
        address.setZipCode("91452");

        order.setBillingAddress(address);

        orderRepository.save(order);
    }

    @Test
    void getOrderMethod() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order.toString());
    }

    @Test
    void upadateOrderMethod() {
        Order order = orderRepository.findById(1L).get();
        order.setStatus("DELIVERED");
        order.getBillingAddress().setZipCode("54806");

        // "save()" Method works in two ways :
        // ==> Save new Entity and Update existing Entity
        orderRepository.save(order);
    }

    @Test
    void deleteOrderMethod() {
        // Hibernate will delete the specific "order" and its associated Tables
        orderRepository.deleteById(1L);
    }
}
