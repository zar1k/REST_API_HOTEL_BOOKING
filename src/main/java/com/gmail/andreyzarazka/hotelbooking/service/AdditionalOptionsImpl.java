package com.gmail.andreyzarazka.hotelbooking.service;

import com.gmail.andreyzarazka.hotelbooking.domain.AdditionalOptions;
import com.gmail.andreyzarazka.hotelbooking.repository.AdditionalOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalOptionsImpl implements AdditionalOptionsService {
    private AdditionalOptionsRepository repository;

    @Autowired
    public AdditionalOptionsImpl(final AdditionalOptionsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AdditionalOptions> getAll() {
        return (List<AdditionalOptions>) this.repository.findAll();
    }
}
