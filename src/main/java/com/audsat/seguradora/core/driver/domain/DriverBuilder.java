package com.audsat.seguradora.core.driver.domain;

import com.audsat.seguradora.core.customer.domain.Customer;

import java.time.LocalDate;

public final class DriverBuilder {
    private Long id;

    private String document;

    private LocalDate birthdate;

    private Customer customer;

    private DriverBuilder() {
    }

    public static DriverBuilder aDriver() {
        return new DriverBuilder();
    }

    public DriverBuilder withId(final Long id) {
        this.id = id;
        return this;
    }

    public DriverBuilder withDocument(final String document) {
        this.document = document;
        return this;
    }

    public DriverBuilder withBirthdate(final LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public DriverBuilder withCustomer(final Customer customer) {
        this.customer = customer;
        return this;
    }

    public Driver build() {
        final Driver driver = new Driver();
        driver.setId(this.id);
        driver.setDocument(this.document);
        driver.setBirthdate(this.birthdate);
        driver.setCustomer(this.customer);
        return driver;
    }
}
