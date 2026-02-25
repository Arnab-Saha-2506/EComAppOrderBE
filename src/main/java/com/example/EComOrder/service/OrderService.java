package com.example.EComOrder.service;

import com.example.EComOrder.DTO.OrderRequestDTO;
import com.example.EComOrder.DTO.OrderResponseDTO;
import com.example.EComOrder.DTO.OrderSuccessDTO;

public interface OrderService {

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) throws Exception;
    public void updateOrderStatus(OrderSuccessDTO orderSuccessDTO) throws Exception;
}
