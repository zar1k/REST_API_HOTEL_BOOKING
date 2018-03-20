package com.gmail.andreyzarazka.hotelbooking.service.impl;

import com.gmail.andreyzarazka.hotelbooking.repository.AuthorizedCustomerRepository;
import com.gmail.andreyzarazka.hotelbooking.service.AuthorizedCustomerService;
import com.gmail.andreyzarazka.hotelbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizedCustomerServiceImpl implements AuthorizedCustomerService {
    final private AuthorizedCustomerRepository repository;
    final private CustomerService service;

    @Autowired
    public AuthorizedCustomerServiceImpl(final AuthorizedCustomerRepository repository, final CustomerService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public boolean isAuthCustomer(String email) {
        return this.repository.findByCustomer(this.service.getByCustomerEmail(email)) != null;
    }
}