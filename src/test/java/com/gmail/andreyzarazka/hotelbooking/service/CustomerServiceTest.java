package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import com.gmail.andreyzarazka.hotelbooking.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService service;

    @Test
    public void whenCreatingCustomer() {
        Customer customer = this.service.apply(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));
        List<Customer> result = this.service.getAll();
        assertTrue(result.contains(customer));
    }

    @Test
    public void whenSearchCustomerById() {
        Customer customer = this.repository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));
        Customer result = this.service.getByCustomerId(customer.getId());
        assertThat(customer, is(result));
    }

    @Test
    public void whenSearchCustomerByyEmail() {
        Customer customer = this.repository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));
        Customer result = this.service.getByCustomerEmail(customer.getEmail());
        assertThat(customer, is(result));
    }
}