package com.gmail.andreyzarazka.hotelbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.andreyzarazka.hotelbooking.controller.responce.Error;
import com.gmail.andreyzarazka.hotelbooking.controller.responce.Success;
import com.gmail.andreyzarazka.hotelbooking.domain.*;
import com.gmail.andreyzarazka.hotelbooking.service.*;
import org.junit.Ignore;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private AuthorizedCustomerService listService;

    @MockBean
    private BookingSuccessService successService;

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
        Customer customer = new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev");
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

    @Test
    public void whenApplyToSaveCustomer() throws Exception {
        Customer customer = new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev");
        ObjectMapper mapper = new ObjectMapper();
        given(this.listService.isAuthCustomer(customer.getEmail())).willReturn(false);
        given(this.customerService.apply(customer)).willReturn(customer);
        this.mvc.perform(
                post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(customer)
                )
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(new Success<>(customer)))
        );
    }

    @Test
    public void whenCustomerIsAlreadyAddedToList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given(this.listService.isAuthCustomer("test@gmail.com")).willReturn(true);
        this.mvc.perform(
                post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev"))
                )
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(new Error("Customer with this email test@gmail.com is already registered")))
        );
    }

    @Ignore
    @Test
    public void whenApplyToSaveBooking() throws Exception {
        Customer customer = new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev");

        Room room = new Room(115, Category.PRESIDENTIAL, 15000, Status.OCCUPIED);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = new Booking(customer, rooms, startDate, endDate);

        ObjectMapper mapper = new ObjectMapper();
        given(this.successService.isBookingSuccess(booking)).willReturn(false);
        given(this.bookingService.apply(booking)).willReturn(booking);
        this.mvc.perform(
                post("/booking").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(booking)
                )
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(new Success<>(booking)))
        );
    }

    @Ignore
    @Test
    public void whenBookingIsNotPossible() throws Exception {
        Customer customer = new Customer("Ivan", "Ivanov", "test@gmail.com", "Ukraine, Kiev");

        Room room = new Room(15, Category.PRESIDENTIAL, 15000, Status.OCCUPIED);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date startDate = df.parse("2017-09-15 09:57:24.124");
        Date endDate = df.parse("2017-09-20 13:00:00.124");

        Booking booking = new Booking(customer, rooms, startDate, endDate);

        ObjectMapper mapper = new ObjectMapper();
        given(this.successService.isBookingSuccess(booking)).willReturn(true);
        this.mvc.perform(
                post("/booking").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(booking)
                )
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(new Error("Booking are not accepted on these dates: 2017-09-15 09:57:24.124 and 2017-09-20 13:00:00.124.")))
        );
    }
}