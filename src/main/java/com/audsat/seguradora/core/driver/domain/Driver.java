package com.audsat.seguradora.core.driver.domain;

import com.audsat.seguradora.core.commons.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "driver")
public class Driver extends BaseEntity {

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    public Driver() {
    }

    public Driver(
            final Long id,
            final String document,
            final LocalDate birthdate
    ) {
        super(id);
        this.document = document;
        this.birthdate = birthdate;
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
}
