package com.gmail.andreyzarazka.hotelbooking.repository;

import com.gmail.andreyzarazka.hotelbooking.domain.Category;
import com.gmail.andreyzarazka.hotelbooking.domain.Room;
import com.gmail.andreyzarazka.hotelbooking.domain.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public interface RoomRepository extends CrudRepository<Room, Integer> {

    List<Room> findByCategory(Category category);

    List<Room> findByStatus(Status status);

}