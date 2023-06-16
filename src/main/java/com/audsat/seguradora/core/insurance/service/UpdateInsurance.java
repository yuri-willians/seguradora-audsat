package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.service.GetCarById;
import com.audsat.seguradora.core.cardriver.service.VerifyIfCustomerIsMainCarDriver;
import com.audsat.seguradora.core.commons.exception.BusinessRuleException;
import com.audsat.seguradora.core.commons.service.GetEntityById;
import com.audsat.seguradora.core.commons.service.UpdateEntity;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.service.GetCustomerById;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.dto.UpdateInsuranceRequest;
import com.audsat.seguradora.core.insurance.dto.UpdateInsuranceResponse;
import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateInsurance implements UpdateEntity {

    private static final String CUSTOMER_IS_NOT_CAR_MAIN_DRIVER = "Customer is not car main driver.";

    private final InsuranceRepository repository;

    private final VerifyIfCustomerIsMainCarDriver verifyIfCustomerIsMainCarDriver;

    private final GetEntityById<Insurance> getInsuranceById;

    private final GetEntityById<Customer> getCustomerById;

    private final GetEntityById<Car> getCarById;

    @Autowired
    public UpdateInsurance(
            final InsuranceRepository repository,
            final VerifyIfCustomerIsMainCarDriver verifyIfCustomerIsMainCarDriver,
            final GetInsuranceById getInsuranceById,
            final GetCustomerById getCustomerById,
            final GetCarById getCarById
    ) {
        this.repository = repository;
        this.verifyIfCustomerIsMainCarDriver = verifyIfCustomerIsMainCarDriver;
        this.getInsuranceById = getInsuranceById;
        this.getCustomerById = getCustomerById;
        this.getCarById = getCarById;
    }

    public UpdateInsuranceResponse execute(
            final Long idInsurance,
            final UpdateInsuranceRequest request
    ) {
        final var insurance = this.getInsuranceById.execute(idInsurance);
        this.updateInsurance(insurance, request);
        this.checkIfCustomerIsMainDriver(insurance.getIdCustomer(), insurance.getIdCar());
        final var savedInsurance = this.repository.save(insurance);
        return UpdateInsuranceResponse.of(savedInsurance);
    }

    private void updateInsurance(final Insurance insurance, final UpdateInsuranceRequest request) {
        this.maybeUpdateEntity(this.getCarById::execute, insurance::setCar, request::getIdCar);
        this.maybeUpdateEntity(this.getCustomerById::execute, insurance::setCustomer, request::getIdCustomer);
        this.maybeUpdate(insurance::setActive, request::getActive);
    }

    private void checkIfCustomerIsMainDriver(final Long idCustomer, final Long idCar) {
        final var isMainCarDriver = this.verifyIfCustomerIsMainCarDriver.execute(idCustomer, idCar);
        if (!isMainCarDriver) throw new BusinessRuleException(CUSTOMER_IS_NOT_CAR_MAIN_DRIVER);
    }

}
