package com.gmail.andreyzarazka.hotelbooking.repository;

import com.gmail.andreyzarazka.hotelbooking.domain.Booking;
import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    List<Booking> findByCustomer(Customer customer);
}