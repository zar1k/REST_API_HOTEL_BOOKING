package com.gmail.andreyzarazka.hotelbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.andreyzarazka.hotelbooking.domain.*;
import com.gmail.andreyzarazka.hotelbooking.service.BookingService;
import com.gmail.andreyzarazka.hotelbooking.service.CustomerService;
import com.gmail.andreyzarazka.hotelbooking.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private RoomService roomService;

    @MockBean
    private CustomerService customerService;

    @Test
    public void whenExecutingSearchByStatus() throws Exception {
        Room room = new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        ObjectMapper mapper = new ObjectMapper();
        given(this.roomService.getByStatus("OCCUPIED")).willReturn(rooms);
        this.mvc.perform(
                get("/rooms/status/OCCUPIED").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(rooms))
        );
    }

    @Test
    public void whenExecutingSearchByCategory() throws Exception {
        Room room = new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        ObjectMapper mapper = new ObjectMapper();
        given(this.roomService.getByCategory("PRESIDENTIAL")).willReturn(rooms);
        this.mvc.perform(
                get("/rooms/category/PRESIDENTIAL").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(rooms))
        );
    }

    @Test
    public void whenExecutingSearchBookingByIDCustomer() throws Exception {
        Customer customer = new Customer("Ivan", "Ivanov", "Ukraine, Kiev");
        Room room = new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-14 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");
        Booking booking = new Booking(customer, rooms, startDate, endDate);
        List<Booking> list = Collections.singletonList(booking);

        ObjectMapper mapper = new ObjectMapper();
        given(this.bookingService.getByCustomer(1)).willReturn(list);
        this.mvc.perform(
                get("/booking/1").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(list))
        );
    }

    @Test
    public void whenReceiveTotalAmountBookingForIDCustomer() throws Exception {
        double totalPrice = 75000;
        ObjectMapper mapper = new ObjectMapper();
        given(this.bookingService.getTotalPrice(1)).willReturn(totalPrice);
        this.mvc.perform(
                get("/booking/price/1").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(totalPrice))
        );
    }
}