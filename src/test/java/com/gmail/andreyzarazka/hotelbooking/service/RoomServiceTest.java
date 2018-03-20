package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.*;
import com.gmail.andreyzarazka.hotelbooking.repository.AdditionalOptionsRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoomServiceTest {
    @Autowired
    private RoomService service;

    @Autowired
    private RoomRepository repository;

    @Autowired
    private AdditionalOptionsRepository optionsRepository;

    @Test
    public void whenGetAllRooms() {
        AdditionalOptions options1 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 100));
        AdditionalOptions options2 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 50));
        AdditionalOptions options3 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 200));
        AdditionalOptions options4 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 100));
        AdditionalOptions options5 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 250));
        AdditionalOptions options6 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 150));
        AdditionalOptions options7 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 500));
        AdditionalOptions options8 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 400));

        List<AdditionalOptions> list1 = new ArrayList<>();
        list1.add(options1);
        list1.add(options2);

        List<AdditionalOptions> list2 = new ArrayList<>();
        list2.add(options3);
        list2.add(options4);

        List<AdditionalOptions> list3 = new ArrayList<>();
        list3.add(options5);
        list3.add(options6);

        List<AdditionalOptions> list4 = new ArrayList<>();
        list4.add(options7);
        list4.add(options8);

        Room singleRoom = this.repository.save(new Room(100, Category.SINGLE, 1000, Status.UNOCCUPIED, list1));
        Room doubleRoom = this.repository.save(new Room(200, Category.DOUBLE, 2000, Status.UNOCCUPIED, list2));
        Room deluxeRoom = this.repository.save(new Room(300, Category.DELUXE, 3000, Status.UNOCCUPIED, list3));
        Room presidentialRoom = this.repository.save(new Room(400, Category.PRESIDENTIAL, 5000, Status.UNOCCUPIED, list4));

        List<Room> actual = new ArrayList<>();
        actual.add(singleRoom);
        actual.add(doubleRoom);
        actual.add(deluxeRoom);
        actual.add(presidentialRoom);

        List<Room> result = this.service.getAll();
        assertThat(actual, is(result));
    }

    @Test
    public void whenRoomsFilteredByCategory() {
        AdditionalOptions options1 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 100));
        AdditionalOptions options2 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 50));
        AdditionalOptions options3 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 200));
        AdditionalOptions options4 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 100));
        AdditionalOptions options5 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 250));
        AdditionalOptions options6 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 150));
        AdditionalOptions options7 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 500));
        AdditionalOptions options8 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 400));

        List<AdditionalOptions> list1 = new ArrayList<>();
        list1.add(options1);
        list1.add(options2);

        List<AdditionalOptions> list2 = new ArrayList<>();
        list2.add(options3);
        list2.add(options4);

        List<AdditionalOptions> list3 = new ArrayList<>();
        list3.add(options5);
        list3.add(options6);

        List<AdditionalOptions> list4 = new ArrayList<>();
        list4.add(options7);
        list4.add(options8);

        Room single1Room = this.repository.save(new Room(100, Category.SINGLE, 1000, Status.UNOCCUPIED, list1));
        Room single2Room = this.repository.save(new Room(150, Category.SINGLE, 1000, Status.UNOCCUPIED, list1));
        Room doubleRoom = this.repository.save(new Room(200, Category.DOUBLE, 2000, Status.UNOCCUPIED, list2));
        Room deluxeRoom = this.repository.save(new Room(300, Category.DELUXE, 3000, Status.UNOCCUPIED, list3));
        Room presidentialRoom = this.repository.save(new Room(400, Category.PRESIDENTIAL, 5000, Status.UNOCCUPIED, list4));

        List<Room> actual = new ArrayList<>();
        actual.add(single1Room);
        actual.add(single2Room);

        List<Room> result = this.service.getByCategory("single");
        assertThat(actual, is(result));
    }

    @Test
    public void whenRoomsFilteredByStatus() {
        AdditionalOptions options1 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 100));
        AdditionalOptions options2 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 50));
        AdditionalOptions options3 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 200));
        AdditionalOptions options4 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 100));
        AdditionalOptions options5 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 250));
        AdditionalOptions options6 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 150));
        AdditionalOptions options7 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 500));
        AdditionalOptions options8 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 400));

        List<AdditionalOptions> list1 = new ArrayList<>();
        list1.add(options1);
        list1.add(options2);

        List<AdditionalOptions> list2 = new ArrayList<>();
        list2.add(options3);
        list2.add(options4);

        List<AdditionalOptions> list3 = new ArrayList<>();
        list3.add(options5);
        list3.add(options6);

        List<AdditionalOptions> list4 = new ArrayList<>();
        list4.add(options7);
        list4.add(options8);

        Room single1Room = this.repository.save(new Room(100, Category.SINGLE, 1000, Status.UNOCCUPIED, list1));
        Room single2Room = this.repository.save(new Room(150, Category.SINGLE, 1000, Status.UNOCCUPIED, list1));
        Room doubleRoom = this.repository.save(new Room(200, Category.DOUBLE, 2000, Status.UNOCCUPIED, list2));
        Room deluxeRoom = this.repository.save(new Room(300, Category.DELUXE, 3000, Status.UNOCCUPIED, list3));
        Room presidentialRoom = this.repository.save(new Room(400, Category.PRESIDENTIAL, 5000, Status.OCCUPIED, list4));

        List<Room> actual = new ArrayList<>();
        actual.add(presidentialRoom);

        List<Room> result = this.service.getByStatus("occupied");
        assertThat(actual, is(result));
    }

    @Test
    public void whenSearchRoomForRoomNumber() {
        AdditionalOptions options1 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 100));
        AdditionalOptions options2 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 50));
        AdditionalOptions options3 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 200));
        AdditionalOptions options4 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 100));

        List<AdditionalOptions> list1 = new ArrayList<>();
        list1.add(options1);
        list1.add(options2);

        List<AdditionalOptions> list2 = new ArrayList<>();
        list2.add(options3);
        list2.add(options4);

        Room singleRoom = this.repository.save(new Room(100, Category.SINGLE, 1000, Status.UNOCCUPIED, list1));
        Room doubleRoom = this.repository.save(new Room(200, Category.DOUBLE, 2000, Status.UNOCCUPIED, list2));

        Room result = this.service.getByRoomNumber(100);
        assertThat(singleRoom, is(result));
    }
}