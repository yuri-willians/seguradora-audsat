package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.service.GetCarById;
import com.audsat.seguradora.core.commons.service.GetEntityById;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.service.GetCustomerById;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.domain.InsuranceBuilder;
import com.audsat.seguradora.core.insurance.dto.CreateInsuranceRequest;
import com.audsat.seguradora.core.insurance.dto.CreateInsuranceResponse;
import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateInsurance {

    private final InsuranceRepository repository;

    private final GetEntityById<Car> getCarById;

    private final GetEntityById<Customer> getCustomerById;

    @Autowired
    public CreateInsurance(
            final InsuranceRepository repository,
            final GetCarById getCarById,
            final GetCustomerById getCustomerById
    ) {
        this.repository = repository;
        this.getCarById = getCarById;
        this.getCustomerById = getCustomerById;
    }


    public CreateInsuranceResponse execute(final CreateInsuranceRequest request) {
        final var insurance = this.buildInsurance(request);
        final var savedInsurance = this.repository.save(insurance);
        return CreateInsuranceResponse.of(savedInsurance);
    }

    private Insurance buildInsurance(final CreateInsuranceRequest request) {
        return InsuranceBuilder.anInsurance()
                .withCar(this.getCar(request))
                .withActive(request.getActive())
                .withCustomer(this.getCustomer(request))
                .withCreationDate(LocalDateTime.now())
                .build();
    }

    private Customer getCustomer(final CreateInsuranceRequest request) {
        return this.getCustomerById.execute(request.getIdCustomer());
    }

    private Car getCar(final CreateInsuranceRequest request) {
        return this.getCarById.execute(request.getIdCar());
    }

}
