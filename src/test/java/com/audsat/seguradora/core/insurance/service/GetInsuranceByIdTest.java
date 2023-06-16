package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.commons.exception.EntityNotFoundException;
import com.audsat.seguradora.core.insurance.domain.Insurance;
import com.audsat.seguradora.core.insurance.domain.InsuranceBuilder;
import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("insurance")})
@DisplayName("Test service GetInsuranceById")
@ExtendWith(MockitoExtension.class)
class GetInsuranceByIdTest {

    private static final String INSURANCE_NOT_FOUND = "Insurance not found.";

    private static final long ID_INSURANCE = 1L;

    @Mock
    private InsuranceRepository repository;

    private GetInsuranceById sut;

    private static Insurance buildInsurance() {
        return InsuranceBuilder.anInsurance()
                .withId(ID_INSURANCE)
                .build();
    }

    @BeforeEach
    void setUp() {
        this.sut = new GetInsuranceById(this.repository);
    }

    @Test
    @DisplayName("Must return customer")
    void test1() {
        final var insurance = buildInsurance();

        Mockito.when(this.repository.findById(ID_INSURANCE))
                .thenReturn(Optional.of(insurance));

        final var response = this.sut.execute(ID_INSURANCE);
        Assertions.assertEquals(ID_INSURANCE, response.getId());
        Mockito.verify(this.repository, times(1))
                .findById(ID_INSURANCE);
    }

    @Test
    @DisplayName("Must throw exception")
    void test2() {
        Mockito.when(this.repository.findById(ID_INSURANCE))
                .thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(EntityNotFoundException.class, () -> this.sut.execute(ID_INSURANCE));
        Assertions.assertEquals(INSURANCE_NOT_FOUND, exception.getMessage());
        Mockito.verify(this.repository, times(1))
                .findById(ID_INSURANCE);
    }
}
