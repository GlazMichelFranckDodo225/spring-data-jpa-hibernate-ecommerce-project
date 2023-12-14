package com.dgmf.service;

import com.dgmf.dto.OrderDtoRequest;
import com.dgmf.dto.OrderDtoResponse;

public interface OrderService {
    OrderDtoResponse placeOrder(OrderDtoRequest orderDtoRequest);
}
