package com.audsat.seguradora.core.customer.service;

import com.audsat.seguradora.core.commons.service.GetEntityById;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class GetCustomerById implements GetEntityById<Customer> {

    private static final String CUSTOMER_NOT_FOUND = "Customer not found.";

    private final CustomerRepository repository;

    @Autowired
    public GetCustomerById(final CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Function<Long, Optional<Customer>> entityProvider() {
        return this.repository::findById;
    }

    @Override
    public String notFoundMessageProvider() {
        return CUSTOMER_NOT_FOUND;
    }

}
