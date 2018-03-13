package com.gmail.andreyzarazka.hotelbooking.repository;

import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByEmail(String email);
}