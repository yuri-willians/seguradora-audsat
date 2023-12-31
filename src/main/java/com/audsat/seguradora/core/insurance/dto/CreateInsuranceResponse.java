package com.audsat.seguradora.core.insurance.dto;

import com.audsat.seguradora.core.insurance.domain.Insurance;

public class CreateInsuranceResponse {

    private final Long idInsurance;

    private CreateInsuranceResponse(final Long idInsurance) {
        this.idInsurance = idInsurance;
    }

    public static CreateInsuranceResponse of(final Insurance insurance) {
        return new CreateInsuranceResponse(insurance.getId());
    }

    public Long getIdInsurance() {
        return this.idInsurance;
    }
}
