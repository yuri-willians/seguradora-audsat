package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.commons.service.GetEntityById;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class GetInsuranceById implements GetEntityById<Insurance> {

    private static final String INSURANCE_NOT_FOUND = "Insurance not found.";

    private final InsuranceRepository repository;

    @Autowired
    public GetInsuranceById(final InsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Function<Long, Optional<Insurance>> entityProvider() {
        return this.repository::findById;
    }

    @Override
    public String notFoundMessageProvider() {
        return INSURANCE_NOT_FOUND;
    }

}
