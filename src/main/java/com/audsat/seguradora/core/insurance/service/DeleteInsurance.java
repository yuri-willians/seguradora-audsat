package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteInsurance {

    private final InsuranceRepository repository;


    @Autowired
    public DeleteInsurance(final InsuranceRepository repository) {
        this.repository = repository;
    }

    public void execute(final Long idInsurance) {
        this.repository.deleteById(idInsurance);
    }
}
