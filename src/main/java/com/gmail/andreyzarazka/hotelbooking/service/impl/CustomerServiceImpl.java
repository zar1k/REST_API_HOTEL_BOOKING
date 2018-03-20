package com.gmail.andreyzarazka.hotelbooking.service.impl;

import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import com.gmail.andreyzarazka.hotelbooking.repository.CustomerRepository;
import com.gmail.andreyzarazka.hotelbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer apply(Customer customer) {
        return this.repository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return (List<Customer>) this.repository.findAll();
    }

    @Override
    public Customer getByCustomerId(int customerId) {
        return this.repository.findById(customerId).get();
    }

    @Override
    public Customer getByCustomerEmail(String email) {
        return this.repository.findByEmail(email);
    }
}