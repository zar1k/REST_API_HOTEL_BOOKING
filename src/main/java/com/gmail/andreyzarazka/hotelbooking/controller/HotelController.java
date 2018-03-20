package com.gmail.andreyzarazka.hotelbooking.controller;

import com.gmail.andreyzarazka.hotelbooking.controller.responce.Error;
import com.gmail.andreyzarazka.hotelbooking.controller.responce.Result;
import com.gmail.andreyzarazka.hotelbooking.controller.responce.Success;
import com.gmail.andreyzarazka.hotelbooking.domain.Booking;
import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import com.gmail.andreyzarazka.hotelbooking.domain.Room;
import com.gmail.andreyzarazka.hotelbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final RoomService roomService;
    private final BookingService bookingService;
    private final CustomerService customerService;
    private final AuthorizedCustomerService authCustomerService;
    private final BookingSuccessService successService;

    @Autowired
    public HotelController(final RoomService roomService, final BookingService bookingService, final CustomerService customerService,
                           final AuthorizedCustomerService authCustomerService, final BookingSuccessService successService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.authCustomerService = authCustomerService;
        this.successService = successService;
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
    public Result applyBooking(@RequestBody Booking booking) {
        final Result result;
        if (!this.successService.isBookingSuccess(booking)) {
            result = new Success<>(this.bookingService.apply(booking));
        } else {
            result = new Error(String.format("Booking are not accepted on these dates: %s and %s.", booking.getStartDate(), booking.getEndDate()));
        }
        return result;
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
    public Result applyCustomer(@RequestBody Customer customer) {
        final Result result;
        if (!this.authCustomerService.isAuthCustomer(customer.getEmail())) {
            result = new Success<>(this.customerService.apply(customer));
        } else {
            result = new Error(String.format("Customer with this email %s is already registered", customer.getEmail()));
        }
        return result;
    }
}