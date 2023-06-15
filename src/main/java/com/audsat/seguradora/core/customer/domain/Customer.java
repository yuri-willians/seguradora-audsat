package com.audsat.seguradora.core.customer.domain;

import com.audsat.seguradora.core.commons.domain.BaseEntity;
import com.audsat.seguradora.core.driver.domain.Driver;
import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    public Customer() {
    }

    public Customer(
            final Long id,
            final String name,
            final Driver driver
    ) {
        super(id);
        this.name = name;
        this.driver = driver;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }
}
