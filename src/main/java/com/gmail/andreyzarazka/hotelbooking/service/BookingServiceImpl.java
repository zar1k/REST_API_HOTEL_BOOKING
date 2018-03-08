package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.AdditionalOptions;
import com.gmail.andreyzarazka.hotelbooking.domain.Booking;
import com.gmail.andreyzarazka.hotelbooking.domain.Customer;
import com.gmail.andreyzarazka.hotelbooking.domain.Room;
import com.gmail.andreyzarazka.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(final BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Booking> getAll() {
        return (List<Booking>) this.repository.findAll();
    }

    @Override
    public Booking apply(final Booking booking) {
        return this.repository.save(booking);
    }

    @Override
    public List<Booking> getByCustomer(int customerId) {
        return this.repository.findByCustomer(new Customer(customerId));
    }

    @Override
    public double getTotalPrice(int bookingId) {
        double totalPrice;
        double roomPrice = 0.0;
        double optionsPrice = 0.0;
        Date startDate = null;
        Date endDate = null;

        List<Booking> bookings = getByCustomer(bookingId);
        for (Booking booking : bookings) {
            startDate = booking.getStartDate();
            endDate = booking.getEndDate();
            List<Room> rooms = booking.getRooms();
            for (Room room : rooms) {
                roomPrice += room.getPrice();
                List<AdditionalOptions> options = room.getOptions();
                for (AdditionalOptions options1 : options) {
                    optionsPrice += options1.getPrice();
                }
            }
        }
        long datesPeriod = getDatesPeriod(startDate, endDate);
        totalPrice = datesPeriod * (roomPrice + optionsPrice);
        return totalPrice;
    }

    private long getDatesPeriod(Date startDate, Date endDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant start = startDate.toInstant();
        Instant end = endDate.toInstant();
        return DAYS.between(start.atZone(defaultZoneId).toLocalDateTime(), end.atZone(defaultZoneId).toLocalDateTime());
    }
}