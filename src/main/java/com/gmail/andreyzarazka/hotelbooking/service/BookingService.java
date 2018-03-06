package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();

    Booking create(Booking booking);

    List<Booking> getByCustomer(int customerId);

    double getTotalPrice(int bookingId);
}