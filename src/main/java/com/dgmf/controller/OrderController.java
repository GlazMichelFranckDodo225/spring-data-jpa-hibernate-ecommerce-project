package com.dgmf.controller;

import com.dgmf.dto.OrderDtoRequest;
import com.dgmf.dto.OrderDtoResponse;
import com.dgmf.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDtoResponse> placeOrder(
            @RequestBody OrderDtoRequest orderDtoRequest
    ) {
        return ResponseEntity.ok(orderService.placeOrder(orderDtoRequest));
    }
}
