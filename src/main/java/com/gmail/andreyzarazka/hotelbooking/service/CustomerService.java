package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Customer;

import java.util.List;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public interface CustomerService {

    Customer apply(Customer customer);

    List<Customer> getAll();

    Customer getByCustomerId(int customerId);

    Customer getByCustomerEmail(String email);

}