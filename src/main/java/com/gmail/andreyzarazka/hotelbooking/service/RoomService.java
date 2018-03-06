package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Category;
import com.gmail.andreyzarazka.hotelbooking.domain.Room;
import com.gmail.andreyzarazka.hotelbooking.domain.Status;

import java.util.List;

public interface RoomService {
    List<Room> getAll();

    List<Room> getByCategory(Category category);

    List<Room> getByStatus(Status status);
}