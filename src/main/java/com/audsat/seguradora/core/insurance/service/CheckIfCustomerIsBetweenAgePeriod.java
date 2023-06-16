package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckIfCustomerIsBetweenAgePeriod {

    private static final Long MIN_AGE = 18L;

    private static final Long MAX_AGE = 25L;

    private final InsuranceRepository repository;

    @Autowired
    public CheckIfCustomerIsBetweenAgePeriod(final InsuranceRepository repository) {
        this.repository = repository;
    }

    public Boolean execute(final Long idInsurance) {
        return this.repository.isCustomerBetweenAgePeriod(idInsurance, MIN_AGE, MAX_AGE);
    }

}
