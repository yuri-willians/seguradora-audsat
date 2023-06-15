package com.audsat.seguradora.core.insurance.dto;

import jakarta.validation.constraints.NotNull;

public class CreateInsuranceRequest {

    @NotNull(message = "{value} cannot be null")
    private final Long idCustomer;

    @NotNull(message = "{value} cannot be null")
    private final Long idCar;

    @NotNull(message = "{value} cannot be null")
    private final Boolean isActive;

    public CreateInsuranceRequest(
            final Long idCustomer,
            final Long idCar,
            final Boolean isActive
    ) {
        this.idCustomer = idCustomer;
        this.idCar = idCar;
        this.isActive = isActive;
    }

    public Long getIdCustomer() {
        return this.idCustomer;
    }

    public Long getIdCar() {
        return this.idCar;
    }

    public Boolean getActive() {
        return this.isActive;
    }
}
