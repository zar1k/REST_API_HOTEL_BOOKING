package com.gmail.andreyzarazka.hotelbooking.controller;

import com.gmail.andreyzarazka.hotelbooking.domain.*;
import com.gmail.andreyzarazka.hotelbooking.repository.AdditionalOptionsRepository;
import com.gmail.andreyzarazka.hotelbooking.repository.RoomRepository;
import com.gmail.andreyzarazka.hotelbooking.service.AdditionalOptionsService;
import com.gmail.andreyzarazka.hotelbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    private final RoomService service;
    private final RoomRepository repository;

    private final AdditionalOptionsService optionsService;
    private final AdditionalOptionsRepository optionsRepository;

    @Autowired
    public HotelController(RoomService service, RoomRepository repository,
                           AdditionalOptionsService optionsService,
                           AdditionalOptionsRepository optionsRepository) {
        this.service = service;
        this.repository = repository;
        this.optionsService = optionsService;
        this.optionsRepository = optionsRepository;
    }

    @RequestMapping("/save")
    public String save() {
        AdditionalOptions options1 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 800));
        AdditionalOptions options2 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.CLEANING, 850));
        AdditionalOptions options3 = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 800));


        List<AdditionalOptions> list1 = new ArrayList<>();
        list1.add(options1);

        List<AdditionalOptions> list2 = new ArrayList<>();
        list2.add(options2);
        list2.add(options3);

        Room room1 = this.repository.save(new Room(14, Category.DOUBLE, 788, Status.OCCUPIED));
        Room room2 = this.repository.save(new Room(15, Category.DELUXE, 1008, Status.OCCUPIED, list1));
        Room room3 = this.repository.save(new Room(34, Category.PRESIDENTIAL, 6688, Status.UNOCCUPIED, list2));
        return "object ROOM save";
    }

    @RequestMapping("/opt")
    public String saveOptions() {
        AdditionalOptions options = this.optionsRepository.save(new AdditionalOptions(TypeOptions.BREAKFAST, 800));
        return "object ADD_OPT save";
    }

    @GetMapping("/rooms")
    public List<Room> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/rooms/status/{status}")
    public List<Room> findByStatus(@PathVariable Status status) {
        return this.service.getByStatus(status);
    }

    @GetMapping("/rooms/category/{category}")
    public List<Room> findByCategory(@PathVariable Category category) {
        return this.service.getByCategory(category);
    }
}