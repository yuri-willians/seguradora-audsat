package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.domain.CarBuilder;
import com.audsat.seguradora.core.car.service.GetCarById;
import com.audsat.seguradora.core.cardriver.service.VerifyIfCustomerIsMainCarDriver;
import com.audsat.seguradora.core.commons.exception.BusinessRuleException;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.domain.CustomerBuilder;
import com.audsat.seguradora.core.customer.service.GetCustomerById;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.domain.InsuranceBuilder;
import com.audsat.seguradora.core.insurance.dto.CreateInsuranceRequest;
import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("insurance")})
@DisplayName("Test service CreateInsurance")
@ExtendWith(MockitoExtension.class)
class CreateInsuranceTest {

    private static final long ID_CAR = 1L;

    private static final long ID_CUSTOMER = 2L;

    private static final long ID_INSURANCE = 3L;

    private static final String CUSTOMER_IS_NOT_CAR_MAIN_DRIVER = "Customer is not car main driver.";

    @Mock
    private InsuranceRepository repository;

    @Mock
    private VerifyIfCustomerIsMainCarDriver verifyIfCustomerIsMainCarDriver;

    @Mock
    private GetCarById getCarById;

    @Mock
    private GetCustomerById getCustomerById;

    private CreateInsurance sut;

    private static Customer buildCustomer() {
        return CustomerBuilder.aCustomer()
                .withId(ID_CUSTOMER)
                .build();
    }

    private static Car buildCar() {
        return CarBuilder.aCar()
                .withId(ID_CAR)
                .build();
    }

    private static Insurance buildInsurance(final Car car, final Customer customer) {
        return InsuranceBuilder.anInsurance()
                .withId(ID_INSURANCE)
                .withCar(car)
                .withCustomer(customer)
                .withActive(true)
                .build();
    }

    private static CreateInsuranceRequest buildRequest() {
        return new CreateInsuranceRequest(
                ID_CUSTOMER,
                ID_CAR,
                true
        );
    }

    @BeforeEach
    void setUp() {
        this.sut = new CreateInsurance(
                this.repository,
                this.verifyIfCustomerIsMainCarDriver,
                this.getCarById,
                this.getCustomerById
        );
    }

    @Test
    @DisplayName("Must create new insurance")
    void test1() {
        final var car = buildCar();
        final var customer = buildCustomer();
        final var insurance = buildInsurance(car, customer);
        final var request = buildRequest();

        Mockito.when(this.getCarById.execute(ID_CAR))
                .thenReturn(car);
        Mockito.when(this.getCustomerById.execute(ID_CUSTOMER))
                .thenReturn(customer);
        Mockito.when(this.verifyIfCustomerIsMainCarDriver.execute(ID_CUSTOMER, ID_CAR))
                .thenReturn(Boolean.TRUE);
        Mockito.when(this.repository.save(Mockito.any()))
                .thenReturn(insurance);

        final var response = this.sut.execute(request);
        Assertions.assertEquals(ID_INSURANCE, response.getIdInsurance());
        Mockito.verify(this.getCarById, times(1))
                .execute(ID_CAR);
        Mockito.verify(this.getCustomerById, times(1))
                .execute(ID_CUSTOMER);
        Mockito.verify(this.verifyIfCustomerIsMainCarDriver, times(1))
                .execute(ID_CUSTOMER, ID_CAR);
        Mockito.verify(this.repository, times(1))
                .save(Mockito.any());
    }

    @Test
    @DisplayName("Must throw exception if customer is not main driver")
    void test2() {
        final var car = buildCar();
        final var customer = buildCustomer();
        final var request = buildRequest();

        Mockito.when(this.getCarById.execute(ID_CAR))
                .thenReturn(car);
        Mockito.when(this.getCustomerById.execute(ID_CUSTOMER))
                .thenReturn(customer);
        Mockito.when(this.verifyIfCustomerIsMainCarDriver.execute(ID_CUSTOMER, ID_CAR))
                .thenReturn(Boolean.FALSE);

        final var exception = Assertions.assertThrows(BusinessRuleException.class, () -> this.sut.execute(request));
        Assertions.assertEquals(CUSTOMER_IS_NOT_CAR_MAIN_DRIVER, exception.getMessage());
        Mockito.verify(this.getCarById, times(1))
                .execute(ID_CAR);
        Mockito.verify(this.getCustomerById, times(1))
                .execute(ID_CUSTOMER);
        Mockito.verify(this.verifyIfCustomerIsMainCarDriver, times(1))
                .execute(ID_CUSTOMER, ID_CAR);
    }
}
