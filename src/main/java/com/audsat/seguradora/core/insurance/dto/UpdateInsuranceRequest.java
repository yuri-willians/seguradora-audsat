package com.audsat.seguradora.core.insurance.dto;

public class UpdateInsuranceRequest {

    private final Long idCustomer;

    private final Long idCar;

    private final Boolean isActive;

    public UpdateInsuranceRequest(
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
