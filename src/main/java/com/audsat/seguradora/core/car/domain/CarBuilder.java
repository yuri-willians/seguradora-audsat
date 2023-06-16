package com.audsat.seguradora.core.car.domain;

public final class CarBuilder {
    private String model;

    private String manufacturer;

    private String year;

    private Double fipe;

    private Long id;

    private CarBuilder() {
    }

    public static CarBuilder aCar() {
        return new CarBuilder();
    }

    public CarBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public CarBuilder withYear(String year) {
        this.year = year;
        return this;
    }

    public CarBuilder withFipe(Double fipe) {
        this.fipe = fipe;
        return this;
    }

    public CarBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public Car build() {
        Car car = new Car();
        car.setModel(model);
        car.setManufacturer(manufacturer);
        car.setYear(year);
        car.setFipe(fipe);
        car.setId(id);
        return car;
    }
}
