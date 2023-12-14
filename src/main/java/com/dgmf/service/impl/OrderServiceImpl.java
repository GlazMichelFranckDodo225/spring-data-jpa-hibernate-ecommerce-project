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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CreditCardPaymentRepository creditCardPaymentRepository;

    @Override
    public OrderDtoResponse placeOrder(OrderDtoRequest orderDtoRequest) {
        // Save Order Details
        Order order = orderDtoRequest.getOrder();
        order.setStatus("IN PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        Order savedOrder = orderRepository.save(order);

        // Save Credit Card Payment Details
        CreditCardPayment creditCardPayment =
                orderDtoRequest.getCreditCardPayment();
        if(!creditCardPayment.getType().equals("DEBIT")) {
            throw new CreditCardPaymentException("Payment Card " +
                    "Type do not Supported");
        }
        creditCardPayment.setOrderId(order.getId());
        CreditCardPayment savedCreditCardPayment =
                creditCardPaymentRepository.save(creditCardPayment);

        // Send back the Response to the Client
        OrderDtoResponse orderDtoResponse = OrderDtoResponse.builder()
                .orderTrackingNumber(order.getOrderTrackingNumber())
                .status(order.getStatus())
                .message("SUCCESS")
                .build();

        return orderDtoResponse;
    }
}
