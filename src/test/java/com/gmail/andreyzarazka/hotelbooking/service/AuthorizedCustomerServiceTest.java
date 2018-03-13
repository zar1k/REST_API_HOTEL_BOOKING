package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import com.gmail.andreyzarazka.hotelbooking.domain.AuthorizedCustomer;
import com.gmail.andreyzarazka.hotelbooking.repository.AuthorizedCustomerRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorizedCustomerServiceTest {
    @Autowired
    private AuthorizedCustomerService service;

    @Autowired
    private AuthorizedCustomerRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenCustomerInCustomerListThenReturnTrue() {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));
        this.repository.save(new AuthorizedCustomer(customer));
        boolean result = this.service.isAuthCustomer(customer.getEmail());
        assertTrue(result);
    }

    @Test
    public void whenCustomerListEmptyThenAnyCustomerNotIn() {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));
        boolean result = this.service.isAuthCustomer(customer.getEmail());
        assertFalse(result);
    }
}