package com.audsat.seguradora.core.customer.domain;

import com.audsat.seguradora.core.commons.domain.BaseEntity;
import com.audsat.seguradora.core.driver.domain.Driver;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @OneToMany(mappedBy = "customer")
    private Set<Insurance> insurances;

    public Customer() {
    }

    public Customer(
            final Long id,
            final String name,
            final Driver driver,
            final Set<Insurance> insurances
    ) {
        super(id);
        this.name = name;
        this.driver = driver;
        this.insurances = insurances;
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

    public Set<Insurance> getInsurances() {
        return this.insurances;
    }

    public void setInsurances(final Set<Insurance> insurances) {
        this.insurances = insurances;
    }

    @Transient
    public String getDocument() {
        return Optional.ofNullable(this.driver)
                .map(Driver::getDocument)
                .orElse(null);
    }

    @Transient
    public LocalDate getBirthdate() {
        return Optional.ofNullable(this.driver)
                .map(Driver::getBirthdate)
                .orElse(null);
    }
}
