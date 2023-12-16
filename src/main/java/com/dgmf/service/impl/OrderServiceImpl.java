package com.dgmf.service.impl;

import com.dgmf.dto.OrderDtoRequest;
import com.dgmf.dto.OrderDtoResponse;
import com.dgmf.entity.CreditCardPayment;
import com.dgmf.entity.Order;
import com.dgmf.exception.CreditCardPaymentException;
import com.dgmf.repository.CreditCardPaymentRepository;
import com.dgmf.repository.OrderRepository;
import com.dgmf.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CreditCardPaymentRepository creditCardPaymentRepository;

    @Override
    @Transactional
    public OrderDtoResponse placeOrder(OrderDtoRequest orderDtoRequest) {
        // Save Order Details
        Order order = orderDtoRequest.getOrder();
        order.setStatus("IN PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        // Order savedOrder = orderRepository.save(order);
        Order savedOrder = orderRepository.saveAndFlush(order);

        // Save Credit Card Payment Details
        CreditCardPayment creditCardPayment = orderDtoRequest.getCreditCardPayment();
        // Checking Credit Card Payment Type
        if(!creditCardPayment.getType().equals("DEBIT")) {
            throw new CreditCardPaymentException("Payment Card Type do not Supported");
        }
        creditCardPayment.setOrderId(order.getId());
        /*CreditCardPayment savedCreditCardPayment = creditCardPaymentRepository.save(creditCardPayment);*/
        CreditCardPayment savedCreditCardPayment = creditCardPaymentRepository
                .saveAndFlush(creditCardPayment);

        // Send back the Response to the Client
        OrderDtoResponse orderDtoResponse = OrderDtoResponse.builder()
                .orderTrackingNumber(order.getOrderTrackingNumber())
                .status(order.getStatus())
                .message("SUCCESS")
                .build();

        return orderDtoResponse;
    }
}
