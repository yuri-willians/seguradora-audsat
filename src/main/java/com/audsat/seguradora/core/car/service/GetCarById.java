package com.audsat.seguradora.core.car.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.repository.CarRepository;
import com.audsat.seguradora.core.commons.service.GetEntityById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class GetCarById implements GetEntityById<Car> {

    private static final String CAR_NOT_FOUND = "Car not found.";

    private final CarRepository repository;

    @Autowired
    public GetCarById(final CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Function<Long, Optional<Car>> entityProvider() {
        return this.repository::findById;
    }

    @Override
    public String notFoundMessageProvider() {
        return CAR_NOT_FOUND;
    }
}
