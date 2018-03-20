package com.gmail.andreyzarazka.hotelbooking.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "booking_success")
public class BookingSuccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinColumn(name = "booking_id")
    private List<Booking> booking = new ArrayList<>();

    public BookingSuccess() {
    }

    public BookingSuccess(List<Booking> booking) {
        this.booking = booking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BookingSuccess)) return false;
        BookingSuccess that = (BookingSuccess) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}