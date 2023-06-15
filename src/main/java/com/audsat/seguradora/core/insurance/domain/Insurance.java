package com.audsat.seguradora.core.insurance.domain;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.commons.domain.BaseEntity;
import com.audsat.seguradora.core.customer.domain.Customer;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "insurance")
public class Insurance extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "creation_dt", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    public Insurance() {
    }

    public Insurance(
            final Long id,
            final Customer customer,
            final LocalDateTime creationDate,
            final LocalDateTime updatedAt,
            final Car car,
            final Boolean active
    ) {
        super(id);
        this.customer = customer;
        this.creationDate = creationDate;
        this.updatedAt = updatedAt;
        this.car = car;
        this.active = active;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(final Car car) {
        this.car = car;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}
