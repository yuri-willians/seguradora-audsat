package com.audsat.seguradora.core.customer.domain;

import com.audsat.seguradora.core.driver.domain.Driver;
import com.audsat.seguradora.core.insurance.domain.Insurance;

import java.util.Set;

public final class CustomerBuilder {
    private Long id;

    private String name;

    private Driver driver;

    private Set<Insurance> insurances;

    private CustomerBuilder() {
    }

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public CustomerBuilder withInsurances(Set<Insurance> insurances) {
        this.insurances = insurances;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setDriver(driver);
        customer.setInsurances(insurances);
        return customer;
    }
}
