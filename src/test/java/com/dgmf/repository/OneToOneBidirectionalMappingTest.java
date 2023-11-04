package com.dgmf.repository;

import com.dgmf.entity.Address;
import com.dgmf.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBidirectionalMappingTest {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveAddressMethod() {
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
        address.setOrder(order);

        addressRepository.save(address);
    }

    @Test
    void updateAddressMethod() {
        Address address = addressRepository.findById(1L).get();
        address.setCity("Lyon");
        address.setStreet("14, Grande Rue");
        address.setZipCode("69008");
        address.setOrder(orderRepository.findById(1L).get());
        address.getOrder().setStatus("DELIVERED");

        addressRepository.save(address);

    }

    @Test
    void fetchAddressMethod() {
        // Hibernate will load Address and its associated Entities
        Address address = addressRepository.findById(1L).get();
    }

    @Test
    void deleteAddressMethod() {
        addressRepository.deleteById(1L);
    }
}
