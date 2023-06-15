package com.audsat.seguradora.core.cardriver.domain;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.commons.domain.BaseEntity;
import com.audsat.seguradora.core.driver.domain.Driver;
import jakarta.persistence.*;

@Entity
@Table(name = "car_driver")
public class CarDriver extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "is_main_driver", nullable = false)
    private Boolean isMainDriver;

    public CarDriver() {
    }

    public CarDriver(
            final Long id,
            final Driver driver,
            final Car car,
            final Boolean isMainDriver
    ) {
        super(id);
        this.driver = driver;
        this.car = car;
        this.isMainDriver = isMainDriver;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(final Car car) {
        this.car = car;
    }

    public Boolean getMainDriver() {
        return this.isMainDriver;
    }

    public void setMainDriver(final Boolean mainDriver) {
        this.isMainDriver = mainDriver;
    }
}
