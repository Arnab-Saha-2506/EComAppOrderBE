package com.example.EComOrder.controller;


import com.example.EComOrder.DTO.OrderRequestDTO;
import com.example.EComOrder.DTO.OrderResponseDTO;
import com.example.EComOrder.DTO.OrderSuccessDTO;
import com.example.EComOrder.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws Exception{
        OrderResponseDTO response = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping
    public ResponseEntity<Void> updateOrderStatus(@RequestBody OrderSuccessDTO orderSuccessDTO) throws Exception{
        orderService.updateOrderStatus(orderSuccessDTO);
        return ResponseEntity.ok().build();
    }
}

