package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Component
public class CalculateBudget {

    private static final double TAX = 0.2;

    private static final Long MIN_AGE = 18L;

    private static final Long MAX_AGE = 25L;

    private final InsuranceRepository repository;

    @Autowired
    public CalculateBudget(final InsuranceRepository repository) {
        this.repository = repository;
    }

    private static AtomicInteger handleFipeTax(
            final Function<? super Insurance, Boolean> validation,
            final Insurance insurance,
            final AtomicInteger fipeTaxMultiplier
    ) {
        if (validation.apply(insurance)) {
            fipeTaxMultiplier.getAndIncrement();
        }
        return fipeTaxMultiplier;
    }

    public Double execute(final Insurance insurance) {
        final var fipeTaxMultiplier = new AtomicInteger(1);
        handleFipeTax(this::isCustomerBetweenAgePeriod, insurance, fipeTaxMultiplier);
        handleFipeTax(this::driverHasAnotherInsurance, insurance, fipeTaxMultiplier);
        handleFipeTax(this::carHasAnotherInsurance, insurance, fipeTaxMultiplier);
        return insurance.getBudget(fipeTaxMultiplier.get(), TAX);
    }

    private Boolean carHasAnotherInsurance(final Insurance insurance) {
        return this.repository.carHasAnotherInsurance(
                insurance.getId(),
                insurance.getIdCar()
        );
    }

    private Boolean driverHasAnotherInsurance(final Insurance insurance) {
        return this.repository.driverHasAnotherInsurance(
                insurance.getId(),
                insurance.getIdCustomer()
        );
    }

    private Boolean isCustomerBetweenAgePeriod(final Insurance insurance) {
        return this.repository.isCustomerBetweenAgePeriod(
                insurance.getId(),
                MIN_AGE,
                MAX_AGE
        );
    }

}
