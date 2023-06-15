package com.audsat.seguradora.core.claim.domain;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.commons.domain.BaseEntity;
import com.audsat.seguradora.core.driver.domain.Driver;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "claim")
public class Claim extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    public Claim() {
    }

    public Claim(
            final Long id,
            final Car car,
            final Driver driver,
            final LocalDate eventDate
    ) {
        super(id);
        this.car = car;
        this.driver = driver;
        this.eventDate = eventDate;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(final Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

    public LocalDate getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(final LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
