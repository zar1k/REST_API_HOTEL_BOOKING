package com.gmail.andreyzarazka.hotelbooking.controller;

import com.gmail.andreyzarazka.hotelbooking.domain.Booking;
import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import com.gmail.andreyzarazka.hotelbooking.domain.Room;
import com.gmail.andreyzarazka.hotelbooking.service.BookingService;
import com.gmail.andreyzarazka.hotelbooking.service.CustomerService;
import com.gmail.andreyzarazka.hotelbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final RoomService roomService;
    private final BookingService bookingService;
    private final CustomerService customerService;

    @Autowired
    public HotelController(final RoomService roomService, final BookingService bookingService, final CustomerService customerService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @GetMapping("/rooms/status/{status}")
    public List<Room> findByStatus(@PathVariable String status) {
        return this.roomService.getByStatus(status);
    }

    @GetMapping("/rooms/category/{category}")
    public List<Room> findByCategory(@PathVariable String category) {
        return this.roomService.getByCategory(category);
    }

    @PostMapping("/booking")
    public void saveBooking(@RequestBody Booking booking) {
        this.bookingService.apply(booking);
    }

    @GetMapping("/booking/{customerId}")
    public List<Booking> findByCustomerId(@PathVariable int customerId) {
        return this.bookingService.getByCustomer(customerId);
    }

    @GetMapping("/booking/price/{customerId}")
    public double getTotalPrice(@PathVariable int customerId) {
        return this.bookingService.getTotalPrice(customerId);
    }

    @PostMapping("/customer")
    public void applyCustomer(@RequestBody Customer customer) {
        this.customerService.apply(customer);
    }
}