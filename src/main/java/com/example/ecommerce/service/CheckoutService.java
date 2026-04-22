package com.example.ecommerce.service;

import com.example.ecommerce.dto.PurchaseDto;
import com.example.ecommerce.dto.PurchaseResponseDto;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final CustomerRepository customerRepository;

    public PurchaseResponseDto placeOrder(PurchaseDto purchaseDto) {

        Order order = purchaseDto.order();
        order.setStatus("PENDING");

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchaseDto.orderItems();
        orderItems.forEach(item -> order.add(item));

        String billingCountry = purchaseDto.billingAddress().getCountry();
        purchaseDto.billingAddress().setCountry(formatCountryName(billingCountry));
        order.setBillingAddress(purchaseDto.billingAddress());

        String shippingCountry = purchaseDto.shippingAddress().getCountry();
        purchaseDto.shippingAddress().setCountry(formatCountryName(shippingCountry));
        order.setShippingAddress(purchaseDto.shippingAddress());

        Customer customer = purchaseDto.customer();

        String email = customer.getEmail();
        Customer existingCustomer = customerRepository.findByEmail(email);

        if (existingCustomer != null) {
            customer = existingCustomer;
        }

        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponseDto(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

    private String formatCountryName(String name) {
        if (name == null || name.isBlank()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
