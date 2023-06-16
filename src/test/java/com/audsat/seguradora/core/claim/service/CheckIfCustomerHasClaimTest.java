package com.audsat.seguradora.core.claim.service;

import com.audsat.seguradora.core.claim.repository.ClaimRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("claim")})
@DisplayName("Test service CheckIfCustomerHasClaim")
@ExtendWith(MockitoExtension.class)
class CheckIfCustomerHasClaimTest {

    private static final long ID_CAR = 1L;

    @Mock
    private ClaimRepository repository;

    private CheckIfCustomerHasClaim sut;

    @BeforeEach
    void setUp() {
        this.sut = new CheckIfCustomerHasClaim(this.repository);
    }

    @Test
    @DisplayName("Must return FALSE if customer dont have claim")
    void test1() {
        Mockito.when(this.repository.customerHasClaim(ID_CAR))
                .thenReturn(Boolean.FALSE);

        final var response = this.sut.execute(ID_CAR);
        Assertions.assertFalse(response);
        Mockito.verify(this.repository, times(1))
                .customerHasClaim(ID_CAR);
    }

    @Test
    @DisplayName("Must return TRUE if customer already have claim")
    void test2() {
        Mockito.when(this.repository.customerHasClaim(ID_CAR))
                .thenReturn(Boolean.TRUE);

        final var response = this.sut.execute(ID_CAR);
        Assertions.assertTrue(response);
        Mockito.verify(this.repository, times(1))
                .customerHasClaim(ID_CAR);
    }
}
