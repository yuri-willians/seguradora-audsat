package com.audsat.seguradora.core.insurance.domain;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.commons.domain.BaseEntity;
import com.audsat.seguradora.core.commons.exception.AttributeNotFoundException;
import com.audsat.seguradora.core.customer.domain.Customer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Transient
    public Long getIdCustomer() {
        return Optional.ofNullable(this.customer)
                .map(BaseEntity::getId)
                .orElse(null);
    }

    @Transient
    public Long getIdCar() {
        return Optional.ofNullable(this.car)
                .map(BaseEntity::getId)
                .orElse(null);
    }

    @Transient
    public Double getBudget(
            final int taxMultiplier,
            final double tax,
            final double baseTax
    ) {
        return this.getFipe() * (baseTax + (tax * taxMultiplier));
    }

    @Transient
    private Double getFipe() {
        return Optional.ofNullable(this.car)
                .map(Car::getFipe)
                .orElseThrow(() -> new AttributeNotFoundException("Fipe value not found."));
    }
}
