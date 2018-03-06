package com.gmail.andreyzarazka.hotelbooking.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_number")
    private int roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_category")
    private Category category;

    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_status")
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalOptions> options = new ArrayList<>();

    public Room() {
    }

    public Room(int roomNumber, Category category, double price, Status status) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.status = status;
    }

    public Room(int roomNumber, Category category, double price, Status status, List<AdditionalOptions> options) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.status = status;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<AdditionalOptions> getOptions() {
        return options;
    }

    public void setOptions(List<AdditionalOptions> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return id == room.id &&
                roomNumber == room.roomNumber &&
                Double.compare(room.price, price) == 0 &&
                category == room.category &&
                status == room.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, category, price, status);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", category=" + category +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}