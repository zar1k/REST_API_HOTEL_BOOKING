package com.gmail.andreyzarazka.hotelbooking.service.impl;

import com.gmail.andreyzarazka.hotelbooking.domain.Booking;
import com.gmail.andreyzarazka.hotelbooking.domain.BookingSuccess;
import com.gmail.andreyzarazka.hotelbooking.domain.Room;
import com.gmail.andreyzarazka.hotelbooking.service.BookingSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookingSuccessServiceImpl implements BookingSuccessService {
    private final ConcurrentHashMap<Integer, BookingSuccess> success = new ConcurrentHashMap<>();

    @Override
    public boolean isBookingSuccess(Booking booking) {
        boolean result = false;
        Integer roomNumber;

        List<Room> rooms = booking.getRooms();
        for (Room room : rooms) {
            roomNumber = room.getRoomNumber();
            if (success.containsKey(roomNumber)) {
                BookingSuccess bookingSuccess = success.get(roomNumber);
                List<Booking> bookingList = bookingSuccess.getBooking();
                for (Booking cacheBooking : bookingList) {
                    result = isDateInBetweenIncludingEndPoints(booking, cacheBooking);
                }
            } else {
                success.put(roomNumber, new BookingSuccess(Collections.singletonList(booking)));
                result = false;
            }
        }
        return result;
    }

    public boolean isDateInBetweenIncludingEndPoints(Booking booking, Booking cacheBooking) {
        Date newStart = booking.getStartDate();
        Date newEnd = booking.getEndDate();
        Date cacheStart = cacheBooking.getStartDate();
        Date cacheEnd = cacheBooking.getEndDate();
        return (cacheStart.compareTo(newStart) >= 0 && cacheStart.compareTo(newEnd) <= 0) ||
                (cacheEnd.compareTo(newStart) >= 0 && cacheEnd.compareTo(newEnd) <= 0) ||
                (newStart.compareTo(cacheStart) >= 0 && newStart.compareTo(cacheEnd) <= 0);
    }
}