package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.*;
import com.gmail.andreyzarazka.hotelbooking.repository.AdditionalOptionsRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.CustomerRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookingServiceTest {
    @Autowired
    private BookingService service;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AdditionalOptionsRepository optionsRepository;

    @Test
    public void whenCreatingBookingWithoutAdditionalOptions() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-14 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.service.apply(new Booking(customer, rooms, startDate, endDate));
        List<Booking> result = this.service.getAll();
        assertTrue(result.contains(booking));
    }

    @Test
    public void whenCreatingBookingWithAdditionalOptions() throws ParseException {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "Ukraine, Kiev"));

        AdditionalOptions breakfast = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 500));
        AdditionalOptions cleaning = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 400));

        List<AdditionalOptions> options = new ArrayList<>();
        options.add(breakfast);
        options.add(cleaning);

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED, options));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-14 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.service.apply(new Booking(customer, rooms, startDate, endDate));
        List<Booking> result = this.service.getAll();
        assertTrue(result.contains(booking));
    }

    @Test
    public void whenSearchBookingForCustomerId() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "Ukraine, Kiev"));

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-14 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.service.apply(new Booking(customer, rooms, startDate, endDate));
        List<Booking> result = this.service.getByCustomer(customer.getId());
        assertThat(booking, is(result.iterator().next()));
    }

    @Test
    @Transactional
    public void whenReceiveTotalPriceOfBookingForWholePeriod() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Ivan", "Ivanov", "Ukraine, Kiev"));

        AdditionalOptions breakfast = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 200));
        AdditionalOptions cleaning = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 100));

        List<AdditionalOptions> options = new ArrayList<>();
        options.add(breakfast);
        options.add(cleaning);

        Room room = this.roomRepository.save(new Room(15, Category.PRESIDENTIAL, 1000, Status.OCCUPIED, options));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = this.service.apply(new Booking(customer, rooms, startDate, endDate));

        double roomPrice = room.getPrice();
        double breakfastPrice = breakfast.getPrice();
        double cleaningPrice = cleaning.getPrice();
        long datesPeriod = 5;
        long actual = (long) ((roomPrice + breakfastPrice + cleaningPrice) * datesPeriod);
        long result = (long) this.service.getTotalPrice(customer.getId());
        assertEquals(actual, result);
    }
}