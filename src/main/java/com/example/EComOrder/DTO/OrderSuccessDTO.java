package com.example.EComOrder.DTO;

import com.example.EComOrder.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSuccessDTO {
    private long orderId;
    private OrderStatus orderStatus;
}
