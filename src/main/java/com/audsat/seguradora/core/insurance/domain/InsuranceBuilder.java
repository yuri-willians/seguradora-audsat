package com.audsat.seguradora.core.insurance.domain;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.customer.domain.Customer;

import java.time.LocalDateTime;

public final class InsuranceBuilder {
    private Long id;

    private Customer customer;

    private LocalDateTime creationDate;

    private LocalDateTime updatedAt;

    private Car car;

    private boolean active;

    private InsuranceBuilder() {
    }

    public static InsuranceBuilder anInsurance() {
        return new InsuranceBuilder();
    }

    public InsuranceBuilder withId(final Long id) {
        this.id = id;
        return this;
    }

    public InsuranceBuilder withCustomer(final Customer customer) {
        this.customer = customer;
        return this;
    }

    public InsuranceBuilder withCreationDate(final LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public InsuranceBuilder withUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public InsuranceBuilder withCar(final Car car) {
        this.car = car;
        return this;
    }

    public InsuranceBuilder withActive(final boolean active) {
        this.active = active;
        return this;
    }

    public Insurance build() {
        final Insurance insurance = new Insurance();
        insurance.setId(this.id);
        insurance.setCustomer(this.customer);
        insurance.setCreationDate(this.creationDate);
        insurance.setUpdatedAt(this.updatedAt);
        insurance.setCar(this.car);
        insurance.setActive(this.active);
        return insurance;
    }
}
