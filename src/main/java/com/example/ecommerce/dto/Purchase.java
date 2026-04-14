package com.example.ecommerce.dto;

import com.example.ecommerce.model.Address;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private final Customer customer;
    private final Address shippingAddress;
    private final Address billingAddress;
    private final Order order;
    private final Set<OrderItem> orderItems;
}
