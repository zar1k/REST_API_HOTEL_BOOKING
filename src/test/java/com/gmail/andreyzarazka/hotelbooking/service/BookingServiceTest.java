package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.*;
import com.gmail.andreyzarazka.hotelbooking.repository.AdditionalOptionsRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.CustomerRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
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
    public void create() throws Exception {
        Customer customer = this.customerRepository.save(new Customer("Andrey", "Petrov", "Ukraine, Kiev"));
        Room room = this.roomRepository.save(new Room(666, Category.PRESIDENTIAL, 15000, Status.UNOCCUPIED));
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startDate = df.parse("2017-09-14 13:57:24");
        Date endDate = df.parse("2017-09-20 13:00:00");
        Booking booking = this.service.create(new Booking(rooms, startDate, endDate, customer));
        List<Booking> result = this.service.getAll();
        assertTrue(result.contains(booking));
    }

    @Test
    public void getByCustomer() throws Exception {
    }

    @Test
    public void getTotalPrice() throws Exception {
    }

}