package com.example.EComOrder.service;


import com.example.EComOrder.DTO.*;
import com.example.EComOrder.clients.ProductClient;
import com.example.EComOrder.entity.OrderEntity;
import com.example.EComOrder.entity.OrderItemsEntity;
import com.example.EComOrder.enums.OrderStatus;
import com.example.EComOrder.mapper.OrderDetailsMapper;
import com.example.EComOrder.mapper.OrderMapper;
import com.example.EComOrder.repository.OrderItemRepository;
import com.example.EComOrder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductClient productClient, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) throws Exception{
        try{
            OrderEntity entity = OrderMapper.toOrderEntity(orderRequestDTO);
//        ProductEntity productEntity = ProductEntity.getProductById(orderRequestDTO.getOrderDetails().getProductId());
            List<OrderItemsEntity> orderItemsEntityArrayList = new ArrayList<>();
            for(OrderDetailsDTO dto: orderRequestDTO.getOrderDetails()){
                ProductsDTO productDetails = productClient.getProductById(dto.getProductId());
                double individualPrice = productDetails.getPrice();
                double totalPrice = individualPrice * dto.getQuantity();

                OrderItemsEntity orderItemsEntity = OrderDetailsMapper.toOrderItemsEntity(
                        dto, entity, individualPrice, totalPrice
                );

                orderItemsEntityArrayList.add(orderItemsEntity);
//            OrderItemsEntity save = orderItemRepository.save(orderItemsEntity);
            }
            entity.setOrderItems(orderItemsEntityArrayList);
            OrderEntity orderEntityResponse = orderRepository.save(entity);
            return OrderMapper.toOrderResponseDTO(orderEntityResponse);
        }
        catch(Exception e){
            throw new Exception("Product not found with productId: " + orderRequestDTO.getOrderDetails().getFirst().getProductId());
        }
    }

    @Override
    public void updateOrderStatus(OrderSuccessDTO orderSuccessDTO) throws Exception{
        try{
            OrderEntity orderDetailsEntity = orderRepository.getReferenceById(orderSuccessDTO.getOrderId());
            orderDetailsEntity.setOrderStatus(OrderStatus.SUCCESSFUL);
            orderRepository.save(orderDetailsEntity);
        }
        catch(Exception e){
            throw new Exception("Order with id " + orderSuccessDTO.getOrderId() + " not found", e);
        }
    }
}
