package com.audsat.seguradora.core.cardriver.service;

import com.audsat.seguradora.core.cardriver.repository.CarDriverRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("cardriver")})
@DisplayName("Test service VerifyIfCustomerIsMainCarDriver")
@ExtendWith(MockitoExtension.class)
class VerifyIfCustomerIsMainCarDriverTest {

    private static final long ID_CAR = 1L;

    private static final long ID_CUSTOMER = 2L;

    @Mock
    private CarDriverRepository repository;

    private VerifyIfCustomerIsMainCarDriver sut;

    @BeforeEach
    void setUp() {
        this.sut = new VerifyIfCustomerIsMainCarDriver(this.repository);
    }

    @Test
    @DisplayName("Must return TRUE if customer is the main car driver")
    void test1() {
        Mockito.when(this.repository.existsMainCarDriverByCustomerAndCar(ID_CUSTOMER, ID_CAR))
                .thenReturn(Boolean.TRUE);

        final var response = this.sut.execute(ID_CUSTOMER, ID_CAR);
        Assertions.assertTrue(response);
        Mockito.verify(this.repository, times(1))
                .existsMainCarDriverByCustomerAndCar(ID_CUSTOMER, ID_CAR);
    }

    @Test
    @DisplayName("Must return FALSE if customer is not the main car driver")
    void test2() {
        Mockito.when(this.repository.existsMainCarDriverByCustomerAndCar(ID_CUSTOMER, ID_CAR))
                .thenReturn(Boolean.FALSE);

        final var response = this.sut.execute(ID_CUSTOMER, ID_CAR);
        Assertions.assertFalse(response);
        Mockito.verify(this.repository, times(1))
                .existsMainCarDriverByCustomerAndCar(ID_CUSTOMER, ID_CAR);
    }
}
