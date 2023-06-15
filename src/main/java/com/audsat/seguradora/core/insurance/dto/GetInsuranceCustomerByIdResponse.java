package com.audsat.seguradora.core.insurance.dto;

import com.audsat.seguradora.core.customer.domain.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class GetInsuranceCustomerByIdResponse {

    private final Long idCustomer;

    private final String name;

    private final String document;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDate birthdate;

    private GetInsuranceCustomerByIdResponse(
            final Long idCustomer,
            final String name,
            final String document,
            final LocalDate birthdate
    ) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.document = document;
        this.birthdate = birthdate;
    }

    public static GetInsuranceCustomerByIdResponse of(final Customer customer) {
        return new GetInsuranceCustomerByIdResponse(
                customer.getId(),
                customer.getName(),
                customer.getDocument(),
                customer.getBirthdate()
        );
    }

    public Long getIdCustomer() {
        return this.idCustomer;
    }

    public String getName() {
        return this.name;
    }

    public String getDocument() {
        return this.document;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }
}
