package com.dgmf.dto;

import com.dgmf.entity.CreditCardPayment;
import com.dgmf.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDtoRequest {
    private Order order;
    private CreditCardPayment creditCardPayment;
}
