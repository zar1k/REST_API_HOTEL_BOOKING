package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.Room;

import java.util.List;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public interface RoomService {

    List<Room> getAll();

    List<Room> getByCategory(String category);

    List<Room> getByStatus(String status);

    List<Room> getByDate(String date);

    Room getByRoomNumber(int roomNumber);

}