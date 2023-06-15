package com.audsat.seguradora.core.insurance.dto;

import com.audsat.seguradora.core.insurance.domain.Insurance;

public class GetInsuranceByIdResponse {

    private final Long idInsurance;

    private final GetInsuranceCarByIdResponse car;

    private final GetInsuranceCustomerByIdResponse customer;

    private final Boolean isActive;

    private final Double budget;

    private GetInsuranceByIdResponse(
            final Long idInsurance,
            final GetInsuranceCarByIdResponse car,
            final GetInsuranceCustomerByIdResponse customer,
            final Boolean isActive,
            final Double budget
    ) {
        this.idInsurance = idInsurance;
        this.car = car;
        this.customer = customer;
        this.isActive = isActive;
        this.budget = budget;
    }

    public static GetInsuranceByIdResponse of(
            final Insurance insurance,
            final Double budget
    ) {
        return new GetInsuranceByIdResponse(
                insurance.getId(),
                GetInsuranceCarByIdResponse.of(insurance.getCar()),
                GetInsuranceCustomerByIdResponse.of(insurance.getCustomer()),
                insurance.isActive(),
                budget
        );
    }

    public Long getIdInsurance() {
        return this.idInsurance;
    }

    public GetInsuranceCarByIdResponse getCar() {
        return this.car;
    }

    public GetInsuranceCustomerByIdResponse getCustomer() {
        return this.customer;
    }

    public Boolean getActive() {
        return this.isActive;
    }

    public Double getBudget() {
        return this.budget;
    }
}
