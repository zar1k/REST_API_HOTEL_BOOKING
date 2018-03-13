package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Customer;

public interface AuthorizedCustomerService {
    boolean isAuthCustomer(String email);
}