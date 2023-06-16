package com.audsat.seguradora.core.insurance.dto;

import com.audsat.seguradora.core.insurance.domain.Insurance;

public class UpdateInsuranceResponse {

    private final Long idInsurance;

    private UpdateInsuranceResponse(final Long idInsurance) {
        this.idInsurance = idInsurance;
    }

    public static UpdateInsuranceResponse of(final Insurance insurance) {
        return new UpdateInsuranceResponse(insurance.getId());
    }

    public Long getIdInsurance() {
        return this.idInsurance;
    }
}
