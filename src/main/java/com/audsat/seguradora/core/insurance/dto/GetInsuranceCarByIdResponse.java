package com.audsat.seguradora.core.insurance.dto;

import com.audsat.seguradora.core.car.domain.Car;

public class GetInsuranceCarByIdResponse {

    private final Long idCar;

    private final String model;

    private final String manufacturer;

    private final String year;

    private final Double fipe;

    private GetInsuranceCarByIdResponse(
            final Long idCar,
            final String model,
            final String manufacturer,
            final String year,
            final Double fipe
    ) {
        this.idCar = idCar;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fipe = fipe;
    }

    public static GetInsuranceCarByIdResponse of(final Car car) {
        return new GetInsuranceCarByIdResponse(
                car.getId(),
                car.getModel(),
                car.getManufacturer(),
                car.getYear(),
                car.getFipe()
        );
    }

    public Long getIdCar() {
        return this.idCar;
    }

    public String getModel() {
        return this.model;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getYear() {
        return this.year;
    }

    public Double getFipe() {
        return this.fipe;
    }
}
