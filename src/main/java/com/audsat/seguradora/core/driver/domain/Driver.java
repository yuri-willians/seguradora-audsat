package com.audsat.seguradora.core.driver.domain;

import com.audsat.seguradora.core.commons.domain.BaseEntity;
import com.audsat.seguradora.core.customer.domain.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "driver")
public class Driver extends BaseEntity {

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @OneToOne(mappedBy = "driver")
    private Customer customer;

    public Driver() {
    }

    public Driver(
            final Long id,
            final String document,
            final LocalDate birthdate,
            final Customer customer
    ) {
        super(id);
        this.document = document;
        this.birthdate = birthdate;
        this.customer = customer;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(final String document) {
        this.document = document;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(final LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
}
