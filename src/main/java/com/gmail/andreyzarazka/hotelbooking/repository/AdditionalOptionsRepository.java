package com.gmail.andreyzarazka.hotelbooking.repository;

import com.gmail.andreyzarazka.hotelbooking.domain.AdditionalOptions;
import org.springframework.data.repository.CrudRepository;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public interface AdditionalOptionsRepository extends CrudRepository<AdditionalOptions, Integer> {
}