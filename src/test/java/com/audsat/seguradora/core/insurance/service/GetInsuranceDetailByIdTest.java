package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.domain.CarBuilder;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.domain.CustomerBuilder;
import com.audsat.seguradora.core.driver.domain.Driver;
import com.audsat.seguradora.core.driver.domain.DriverBuilder;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.domain.InsuranceBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("insurance")})
@DisplayName("Test service GetInsuranceDetailById")
@ExtendWith(MockitoExtension.class)
class GetInsuranceDetailByIdTest {

    private static final long ID_INSURANCE = 1L;

    private static final long ID_CUSTOMER = 2L;

    private static final long ID_DRIVER = 3L;

    private static final long ID_CAR = 4L;

    private static final double FIPE = 10000D;

    @Mock
    private GetInsuranceById getInsuranceById;

    @Mock
    private CalculateBudget calculateBudget;

    private GetInsuranceDetailById sut;

    private static Insurance buildInsurance(
            final Customer customer,
            final Car car
    ) {
        return InsuranceBuilder.anInsurance()
                .withId(ID_INSURANCE)
                .withCustomer(customer)
                .withCar(car)
                .build();
    }

    private static Driver buildDriver() {
        return DriverBuilder.aDriver()
                .withId(ID_DRIVER)
                .build();
    }

    private static Customer buildCustomer(final Driver driver) {
        return CustomerBuilder.aCustomer()
                .withId(ID_CUSTOMER)
                .withDriver(driver)
                .build();
    }

    private static Car buildCar() {
        return CarBuilder.aCar()
                .withId(ID_CAR)
                .withFipe(FIPE)
                .build();
    }

    @BeforeEach
    void setUp() {
        this.sut = new GetInsuranceDetailById(
                this.getInsuranceById,
                this.calculateBudget
        );
    }

    @Test
    @DisplayName("Must return insurance detail if exists")
    void test1() {
        final var car = buildCar();
        final var driver = buildDriver();
        final var customer = buildCustomer(driver);
        final var insurance = buildInsurance(customer, car);

        Mockito.when(this.getInsuranceById.execute(ID_INSURANCE))
                .thenReturn(insurance);
        Mockito.when(this.calculateBudget.execute(insurance))
                .thenReturn(1D);

        final var response = this.sut.execute(ID_INSURANCE);
        Assertions.assertEquals(ID_INSURANCE, response.getIdInsurance());
        Mockito.verify(this.getInsuranceById, times(1))
                .execute(ID_INSURANCE);
        Mockito.verify(this.calculateBudget, times(1))
                .execute(insurance);

    }
}
