package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.commons.service.GetEntityById;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.dto.GetInsuranceByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetInsuranceDetailById {

    private final GetEntityById<Insurance> getInsuranceById;

    private final CalculateBudget calculateBudget;

    @Autowired
    public GetInsuranceDetailById(
            final GetInsuranceById getInsuranceById,
            final CalculateBudget calculateBudget
    ) {
        this.getInsuranceById = getInsuranceById;
        this.calculateBudget = calculateBudget;
    }

    public GetInsuranceByIdResponse execute(final Long idInsurance) {
        final var insurance = this.getInsuranceById.execute(idInsurance);
        return GetInsuranceByIdResponse.of(
                insurance,
                this.calculateBudget.execute(insurance)
        );
    }

}
