package com.audsat.seguradora.core.cardriver.service;

import com.audsat.seguradora.core.cardriver.repository.CarDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyIfCustomerIsMainCarDriver {

    private final CarDriverRepository repository;

    @Autowired
    public VerifyIfCustomerIsMainCarDriver(final CarDriverRepository repository) {
        this.repository = repository;
    }

    public Boolean execute(final Long idCustomer, final Long idCar) {
        return this.repository.existsMainCarDriverByCustomerAndCar(idCustomer, idCar);
    }
}
