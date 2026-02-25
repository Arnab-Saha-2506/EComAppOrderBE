package com.example.EComOrder.repository;

import com.example.EComOrder.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemsEntity, Long> {
}
