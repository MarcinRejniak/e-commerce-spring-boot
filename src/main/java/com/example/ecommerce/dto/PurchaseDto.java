package com.example.ecommerce.dto;

import com.example.ecommerce.model.Address;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;

import java.util.Set;

public record PurchaseDto(Customer customer, Address shippingAddress, Address billingAddress, Order order,
                          Set<OrderItem> orderItems) {

}
