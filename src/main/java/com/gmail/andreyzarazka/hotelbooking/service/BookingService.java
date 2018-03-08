package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Booking;

import java.util.List;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public interface BookingService {
    List<Booking> getAll();

    Booking apply(Booking booking);

    List<Booking> getByCustomer(int customerId);

    double getTotalPrice(int customerId);
}