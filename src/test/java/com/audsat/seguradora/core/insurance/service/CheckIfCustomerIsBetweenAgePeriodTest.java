package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("insurance")})
@DisplayName("Test service CheckIfCustomerIsBetweenAgePeriod")
@ExtendWith(MockitoExtension.class)
class CheckIfCustomerIsBetweenAgePeriodTest {

    private static final long ID_INSURANCE = 1L;

    private static final long ID_CUSTOMER = 2L;

    private static final long ID_DRIVER = 3L;

    private static final int TWENTY_YEARS = 20;

    private static final Long MIN_AGE = 18L;

    private static final Long MAX_AGE = 25L;

    @Mock
    private InsuranceRepository repository;

    private CheckIfCustomerIsBetweenAgePeriod sut;

    @BeforeEach
    void setUp() {
        this.sut = new CheckIfCustomerIsBetweenAgePeriod(this.repository);
    }

    @Test
    @DisplayName("Must return FALSE if customer is off age period")
    void test1() {
        Mockito.when(this.repository.isCustomerBetweenAgePeriod(ID_INSURANCE, MIN_AGE, MAX_AGE))
                .thenReturn(Boolean.FALSE);

        final var response = this.sut.execute(ID_INSURANCE);
        Assertions.assertFalse(response);
        Mockito.verify(this.repository, times(1))
                .isCustomerBetweenAgePeriod(ID_INSURANCE, MIN_AGE, MAX_AGE);
    }

    @Test
    @DisplayName("Must return TRUE if customer is on age period")
    void test2() {
        Mockito.when(this.repository.isCustomerBetweenAgePeriod(ID_INSURANCE, MIN_AGE, MAX_AGE))
                .thenReturn(Boolean.TRUE);

        final var response = this.sut.execute(ID_INSURANCE);
        Assertions.assertTrue(response);
        Mockito.verify(this.repository, times(1))
                .isCustomerBetweenAgePeriod(ID_INSURANCE, MIN_AGE, MAX_AGE);
    }
}
