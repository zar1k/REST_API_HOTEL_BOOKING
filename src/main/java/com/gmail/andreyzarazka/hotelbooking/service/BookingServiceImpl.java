package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Booking;
import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import com.gmail.andreyzarazka.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Booking> getAll() {
        return (List<Booking>) this.repository.findAll();
    }

    @Override
    public Booking create(Booking booking) {
        return this.repository.save(booking);
    }

    @Override
    public List<Booking> getByCustomer(int customerId) {
        return this.repository.findByCustomer(new Customer(customerId));
    }

    @Override
    public double getTotalPrice(int bookingId) {
        return 0;
    }
}