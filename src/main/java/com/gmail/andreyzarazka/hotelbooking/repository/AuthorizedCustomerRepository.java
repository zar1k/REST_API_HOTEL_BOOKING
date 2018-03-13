package com.gmail.andreyzarazka.hotelbooking.repository;

import com.gmail.andreyzarazka.hotelbooking.domain.AuthorizedCustomer;
import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizedCustomerRepository extends CrudRepository<AuthorizedCustomer, Integer> {

    AuthorizedCustomer findByCustomer(Customer customer);
}