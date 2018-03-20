package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.*;
import com.gmail.andreyzarazka.hotelbooking.repository.BookingRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.CustomerRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookingSuccessServiceTest {
    @Autowired
    private BookingSuccessService service;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void whenNewBookingIsPossible() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.bookingRepository.save(new Booking(customer, rooms, startDate, endDate));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        service.isBookingSuccess(booking);

        Date newStartDate = df.parse("2017-09-23 09:57:24.124");
        Date newEndDate = df.parse("2017-09-27 13:00:00.124");

        Booking newBooking = this.bookingRepository.save(new Booking(customer, rooms, newStartDate, newEndDate));

        boolean result = service.isBookingSuccess(newBooking);

        assertFalse(result);
    }

    @Test
    public void whenNumberIsAlreadyBookedForThatDate() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.bookingRepository.save(new Booking(customer, rooms, startDate, endDate));

        service.isBookingSuccess(booking);
        boolean result = service.isBookingSuccess(booking);

        assertTrue(result);
    }

    @Test
    public void whenDateOfNewBookingIsBetweenDatesOfExisting() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.bookingRepository.save(new Booking(customer, rooms, startDate, endDate));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        service.isBookingSuccess(booking);

        Date newStartDate = df.parse("2017-09-16 09:57:24.124");
        Date newEndDate = df.parse("2017-09-19 13:00:00.124");

        Booking newBooking = this.bookingRepository.save(new Booking(customer, rooms, newStartDate, newEndDate));

        boolean result = service.isBookingSuccess(newBooking);

        assertTrue(result);
    }

    @Test
    public void whenDateOfNewBookingIncludesDatesOfExisting() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.bookingRepository.save(new Booking(customer, rooms, startDate, endDate));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        service.isBookingSuccess(booking);

        Date newStartDate = df.parse("2017-09-11 09:57:24.124");
        Date newEndDate = df.parse("2017-09-23 13:00:00.124");

        Booking newBooking = this.bookingRepository.save(new Booking(customer, rooms, newStartDate, newEndDate));

        boolean result = service.isBookingSuccess(newBooking);

        assertTrue(result);
    }

    @Test
    public void whenEndDateOfNewBookingBetweenBatesOfExisting() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.bookingRepository.save(new Booking(customer, rooms, startDate, endDate));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        service.isBookingSuccess(booking);

        Date newStartDate = df.parse("2017-09-11 09:57:24.124");
        Date newEndDate = df.parse("2017-09-17 13:00:00.124");

        Booking newBooking = this.bookingRepository.save(new Booking(customer, rooms, newStartDate, newEndDate));

        boolean result = service.isBookingSuccess(newBooking);

        assertTrue(result);
    }

    @Test
    public void whenStartDateOfNewBookingBetweenBatesOfExisting() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.bookingRepository.save(new Booking(customer, rooms, startDate, endDate));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        service.isBookingSuccess(booking);

        Date newStartDate = df.parse("2017-09-17 09:57:24.124");
        Date newEndDate = df.parse("2017-09-28 13:00:00.124");

        Booking newBooking = this.bookingRepository.save(new Booking(customer, rooms, newStartDate, newEndDate));

        boolean result = service.isBookingSuccess(newBooking);

        assertTrue(result);
    }
}