package com.audsat.seguradora.core.claim.service;

import com.audsat.seguradora.core.claim.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckIfCustomerHasClaim {

    private final ClaimRepository repository;

    @Autowired
    public CheckIfCustomerHasClaim(final ClaimRepository repository) {
        this.repository = repository;
    }

    public Boolean execute(final Long idCustomer) {
        return this.repository.customerHasClaim(idCustomer);
    }
}
