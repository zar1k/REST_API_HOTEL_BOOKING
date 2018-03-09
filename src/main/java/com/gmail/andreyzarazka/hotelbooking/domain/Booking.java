package com.gmail.andreyzarazka.hotelbooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
@Entity(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinColumn(name = "room_id")
    private List<Room> rooms = new ArrayList<>();

    @JsonIgnore
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @JsonIgnore
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Booking() {
    }

    public Booking(Customer customer, List<Room> rooms, Date startDate, Date endDate) {
        this.customer = customer;
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Booking)) return false;
        Booking booking = (Booking) obj;
        return id == booking.id &&
                Objects.equals(startDate, booking.startDate) &&
                Objects.equals(endDate, booking.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", rooms=" + rooms +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}