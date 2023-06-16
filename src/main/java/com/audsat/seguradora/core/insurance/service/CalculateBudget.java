package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.claim.service.CheckIfCarHasClaim;
import com.audsat.seguradora.core.claim.service.CheckIfCustomerHasClaim;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Component
public class CalculateBudget {

    private static final double TAX = 0.02;

    private static final double BASE_TAX = 0.06;

    private final CheckIfCarHasClaim checkIfCarHasClaim;

    private final CheckIfCustomerHasClaim checkIfCustomerHasClaim;

    private final CheckIfCustomerIsBetweenAgePeriod checkIfCustomerIsBetweenAgePeriod;


    @Autowired
    public CalculateBudget(
            final CheckIfCarHasClaim checkIfCarHasClaim,
            final CheckIfCustomerHasClaim checkIfCustomerHasClaim,
            final CheckIfCustomerIsBetweenAgePeriod checkIfCustomerIsBetweenAgePeriod
    ) {
        this.checkIfCarHasClaim = checkIfCarHasClaim;
        this.checkIfCustomerHasClaim = checkIfCustomerHasClaim;
        this.checkIfCustomerIsBetweenAgePeriod = checkIfCustomerIsBetweenAgePeriod;
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
        final var fipeTaxMultiplier = new AtomicInteger(0);
        handleFipeTax(this::isCustomerBetweenAgePeriod, insurance, fipeTaxMultiplier);
        handleFipeTax(this::customerHasClaim, insurance, fipeTaxMultiplier);
        handleFipeTax(this::carHasClaim, insurance, fipeTaxMultiplier);
        return insurance.getBudget(fipeTaxMultiplier.get(), TAX, BASE_TAX);
    }

    private Boolean carHasClaim(final Insurance insurance) {
        return this.checkIfCarHasClaim.execute(insurance.getIdCar());
    }

    private Boolean customerHasClaim(final Insurance insurance) {
        return this.checkIfCustomerHasClaim.execute(insurance.getIdCustomer());
    }

    private Boolean isCustomerBetweenAgePeriod(final Insurance insurance) {
        return this.checkIfCustomerIsBetweenAgePeriod.execute(insurance.getId());
    }

}
