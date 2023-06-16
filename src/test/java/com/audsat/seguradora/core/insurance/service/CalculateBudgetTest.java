package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.domain.CarBuilder;
import com.audsat.seguradora.core.claim.service.CheckIfCarHasClaim;
import com.audsat.seguradora.core.claim.service.CheckIfCustomerHasClaim;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.domain.CustomerBuilder;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.domain.InsuranceBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("insurance")})
@DisplayName("Test service CalculateBudget")
@ExtendWith(MockitoExtension.class)
class CalculateBudgetTest {

    private static final double ZERO_TRUE = 0.06;

    private static final double ONE_TRUE = 0.08;

    private static final double TWO_TRUE = 0.1;

    private static final double THREE_TRUE = 0.12;

    private static final long ID_CAR = 1L;

    private static final long ID_INSURANCE = 2L;

    private static final long ID_CUSTOMER = 3L;

    private static final double FIPE = 10000D;

    @Mock
    private CheckIfCarHasClaim checkIfCarHasClaim;

    @Mock
    private CheckIfCustomerHasClaim checkIfCustomerHasClaim;

    @Mock
    private CheckIfCustomerIsBetweenAgePeriod checkIfCustomerIsBetweenAgePeriod;

    private CalculateBudget sut;

    private static Customer buildCustomer() {
        return CustomerBuilder.aCustomer()
                .withId(ID_CUSTOMER)
                .build();
    }

    private static Car buildCar() {
        return CarBuilder.aCar()
                .withId(ID_CAR)
                .withFipe(FIPE)
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

    @BeforeEach
    void setUp() {
        this.sut = new CalculateBudget(
                this.checkIfCarHasClaim,
                this.checkIfCustomerHasClaim,
                this.checkIfCustomerIsBetweenAgePeriod
        );
    }

    @Test
    @DisplayName("Must return right value if check 1 is false, check 2 is false and check 3 is false")
    void test1() {
        final var car = buildCar();
        final var customer = buildCustomer();
        final var insurance = buildInsurance(car, customer);

        Mockito.when(this.checkIfCarHasClaim.execute(ID_CAR))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.checkIfCustomerHasClaim.execute(ID_CUSTOMER))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.checkIfCustomerIsBetweenAgePeriod.execute(ID_INSURANCE))
                .thenReturn(Boolean.FALSE);

        final var response = this.sut.execute(insurance);
        Assertions.assertEquals(FIPE * ZERO_TRUE, response);
        Mockito.verify(this.checkIfCarHasClaim, times(1))
                .execute(ID_CAR);
        Mockito.verify(this.checkIfCustomerHasClaim, times(1))
                .execute(ID_CUSTOMER);
        Mockito.verify(this.checkIfCustomerIsBetweenAgePeriod, times(1))
                .execute(ID_INSURANCE);
    }

    @Test
    @DisplayName("Must return right value if check 1 is true, check 2 is false and check 3 is false")
    void test2() {
        final var car = buildCar();
        final var customer = buildCustomer();
        final var insurance = buildInsurance(car, customer);

        Mockito.when(this.checkIfCarHasClaim.execute(ID_CAR))
                .thenReturn(Boolean.TRUE);
        Mockito.when(this.checkIfCustomerHasClaim.execute(ID_CUSTOMER))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.checkIfCustomerIsBetweenAgePeriod.execute(ID_INSURANCE))
                .thenReturn(Boolean.FALSE);

        final var response = this.sut.execute(insurance);
        Assertions.assertEquals(FIPE * ONE_TRUE, response);
        Mockito.verify(this.checkIfCarHasClaim, times(1))
                .execute(ID_CAR);
        Mockito.verify(this.checkIfCustomerHasClaim, times(1))
                .execute(ID_CUSTOMER);
        Mockito.verify(this.checkIfCustomerIsBetweenAgePeriod, times(1))
                .execute(ID_INSURANCE);
    }

    @Test
    @DisplayName("Must return right value if check 1 is true, check 2 is true and check 3 is false")
    void test3() {
        final var car = buildCar();
        final var customer = buildCustomer();
        final var insurance = buildInsurance(car, customer);

        Mockito.when(this.checkIfCarHasClaim.execute(ID_CAR))
                .thenReturn(Boolean.TRUE);
        Mockito.when(this.checkIfCustomerHasClaim.execute(ID_CUSTOMER))
                .thenReturn(Boolean.TRUE);
        Mockito.when(this.checkIfCustomerIsBetweenAgePeriod.execute(ID_INSURANCE))
                .thenReturn(Boolean.FALSE);

        final var response = this.sut.execute(insurance);
        Assertions.assertEquals(FIPE * TWO_TRUE, response);
        Mockito.verify(this.checkIfCarHasClaim, times(1))
                .execute(ID_CAR);
        Mockito.verify(this.checkIfCustomerHasClaim, times(1))
                .execute(ID_CUSTOMER);
        Mockito.verify(this.checkIfCustomerIsBetweenAgePeriod, times(1))
                .execute(ID_INSURANCE);
    }

    @Test
    @DisplayName("Must return right value if check 1 is true, check 2 is true and check 3 is true")
    void test4() {
        final var car = buildCar();
        final var customer = buildCustomer();
        final var insurance = buildInsurance(car, customer);

        Mockito.when(this.checkIfCarHasClaim.execute(ID_CAR))
                .thenReturn(Boolean.TRUE);
        Mockito.when(this.checkIfCustomerHasClaim.execute(ID_CUSTOMER))
                .thenReturn(Boolean.TRUE);
        Mockito.when(this.checkIfCustomerIsBetweenAgePeriod.execute(ID_INSURANCE))
                .thenReturn(Boolean.TRUE);

        final var response = this.sut.execute(insurance);
        Assertions.assertEquals(FIPE * THREE_TRUE, response);
        Mockito.verify(this.checkIfCarHasClaim, times(1))
                .execute(ID_CAR);
        Mockito.verify(this.checkIfCustomerHasClaim, times(1))
                .execute(ID_CUSTOMER);
        Mockito.verify(this.checkIfCustomerIsBetweenAgePeriod, times(1))
                .execute(ID_INSURANCE);
    }

}
