package com.example.ecommerce.service;

import com.example.ecommerce.dto.PurchaseDto;
import com.example.ecommerce.dto.PurchaseResponseDto;
import com.example.ecommerce.model.Address;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CheckoutService checkoutService;

    @Test
    void should_place_order_with_normalize_country_names_when_new_customer_submits_purchase() {

//        given
        PurchaseDto purchaseDto = createMockPurchaseDto(
                "pOlAnd", "FranCE", "test@gmail.com");

        given(customerRepository.findByEmail(anyString())).willReturn(null);

//        when
        PurchaseResponseDto response = checkoutService.placeOrder(purchaseDto);

//        then
        assertThat(response).isNotNull();
        assertThat(purchaseDto.shippingAddress().getCountry()).isEqualTo("France");
        assertThat(purchaseDto.billingAddress().getCountry()).isEqualTo("Poland");
        assertThat(purchaseDto.order().getStatus()).isEqualTo("PENDING");
        assertThat(purchaseDto.order().getOrderTrackingNumber()).isNotNull();

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void should_associate_multiple_orders_with_the_same_customer_when_email_already_exists() {

//        given
        String email = "existing.email@gmail.com";
        Long existingId = 1L;

        Customer existingCustomer = new Customer();
        existingCustomer.setId(existingId);
        existingCustomer.setEmail(email);

        Order existingOrder = new Order();
        existingCustomer.add(existingOrder);

        PurchaseDto purchaseDto = createMockPurchaseDto("poland", "France", email);

        given(customerRepository.findByEmail(email)).willReturn(existingCustomer);

//        when
        checkoutService.placeOrder(purchaseDto);

//        then
        assertThat(existingCustomer.getOrders()).hasSize(2);
        existingCustomer.getOrders()
                .forEach(order -> assertThat(order.getCustomer().getId()).isEqualTo(existingId));

        verify(customerRepository).save(existingCustomer);
    }

    private PurchaseDto createMockPurchaseDto(String billingCountry, String shippingCountry, String email) {

        Customer customer = new Customer();
        customer.setEmail(email);

        Address shippingAddress = new Address();
        shippingAddress.setCountry(shippingCountry);

        Address billingAddress = new Address();
        billingAddress.setCountry(billingCountry);

        Order order = new Order();

        return new PurchaseDto(customer, shippingAddress, billingAddress, order, new HashSet<>());
    }
}
