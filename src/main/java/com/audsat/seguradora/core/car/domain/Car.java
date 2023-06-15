package com.audsat.seguradora.core.car.domain;

import com.audsat.seguradora.core.cardriver.domain.CarDriver;
import com.audsat.seguradora.core.claim.domain.Claim;
import com.audsat.seguradora.core.commons.domain.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "car_year", nullable = false)
    private String year;

    @Column(name = "fipe_value", nullable = false)
    private Double fipe;

    @OneToMany(mappedBy = "car")
    private Set<CarDriver> carDrivers;

    @OneToMany(mappedBy = "car")
    private Set<Claim> claims;

    public Car() {
    }

    public Car(
            final Long id,
            final String model,
            final String manufacturer,
            final String year,
            final Double fipe
    ) {
        super(id);
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fipe = fipe;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    public Double getFipe() {
        return this.fipe;
    }

    public void setFipe(final Double fipe) {
        this.fipe = fipe;
    }
}
