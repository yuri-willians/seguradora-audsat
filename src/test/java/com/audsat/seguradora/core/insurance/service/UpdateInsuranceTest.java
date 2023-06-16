package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.domain.CarBuilder;
import com.audsat.seguradora.core.car.service.GetCarById;
import com.audsat.seguradora.core.cardriver.service.VerifyIfCustomerIsMainCarDriver;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.domain.CustomerBuilder;
import com.audsat.seguradora.core.customer.service.GetCustomerById;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.domain.InsuranceBuilder;
import com.audsat.seguradora.core.insurance.dto.UpdateInsuranceRequest;
import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("insurance")})
@DisplayName("Test service UpdateInsurance")
@ExtendWith(MockitoExtension.class)
class UpdateInsuranceTest {

    public static final long ID_CAR = 1L;
    public static final long ID_CUSTOMER = 2L;
    public static final long ID_INSURANCE = 3L;
    @Mock
    private InsuranceRepository repository;

    @Mock
    private VerifyIfCustomerIsMainCarDriver verifyIfCustomerIsMainCarDriver;

    @Mock
    private GetInsuranceById getInsuranceById;

    @Mock
    private GetCustomerById getCustomerById;

    @Mock
    private GetCarById getCarById;

    private UpdateInsurance sut;

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

    private static Insurance buildInsurance(
            final Car car,
            final Customer customer
    ) {
        return InsuranceBuilder.anInsurance()
                .withId(ID_INSURANCE)
                .withCar(car)
                .withCustomer(customer)
                .build();
    }

    private static UpdateInsuranceRequest buildRequest() {
        return new UpdateInsuranceRequest(
                ID_CUSTOMER,
                ID_CAR,
                true
        );
    }

    @BeforeEach
    void setUp() {
        this.sut = new UpdateInsurance(
                this.repository,
                this.verifyIfCustomerIsMainCarDriver,
                this.getInsuranceById,
                this.getCustomerById,
                this.getCarById
        );
    }

    @Test
    @DisplayName("Must update insurance")
    void test1() {
        final var car = buildCar();
        final var customer = buildCustomer();
        final var insurance = buildInsurance(car, customer);
        final var request = buildRequest();

        Mockito.when(this.getInsuranceById.execute(ID_INSURANCE))
                .thenReturn(insurance);
        Mockito.when(this.getCarById.execute(ID_CAR))
                .thenReturn(car);
        Mockito.when(this.getCustomerById.execute(ID_CUSTOMER))
                .thenReturn(customer);
        Mockito.when(this.verifyIfCustomerIsMainCarDriver.execute(ID_CUSTOMER, ID_CAR))
                .thenReturn(Boolean.TRUE);
        Mockito.when(this.repository.save(Mockito.any()))
                .thenReturn(insurance);

        final var response = this.sut.execute(ID_INSURANCE, request);
        Assertions.assertEquals(ID_INSURANCE, response.getIdInsurance());

        Mockito.verify(this.getInsuranceById, times(1))
                .execute(ID_INSURANCE);
        Mockito.verify(this.getCarById, times(1))
                .execute(ID_CAR);
        Mockito.verify(this.getCustomerById, times(1))
                .execute(ID_CUSTOMER);
        Mockito.verify(this.verifyIfCustomerIsMainCarDriver, times(1))
                .execute(ID_CUSTOMER, ID_CAR);
        Mockito.verify(this.repository, times(1))
                .save(Mockito.any());
    }
}
