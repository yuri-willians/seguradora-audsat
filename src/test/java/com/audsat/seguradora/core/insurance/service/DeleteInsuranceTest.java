package com.audsat.seguradora.core.insurance.service;

import com.audsat.seguradora.core.insurance.repository.InsuranceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("insurance")})
@DisplayName("Test service DeleteInsurance")
@ExtendWith(MockitoExtension.class)
class DeleteInsuranceTest {

    private static final long ID_INSURANCE = 1L;

    @Mock
    private InsuranceRepository repository;

    private DeleteInsurance sut;

    @BeforeEach
    void setUp() {
        this.sut = new DeleteInsurance(this.repository);
    }

    @Test
    @DisplayName("Must delete insurance")
    void test1() {
        this.sut.execute(ID_INSURANCE);

        Mockito.verify(this.repository, times(1))
                .deleteById(ID_INSURANCE);
    }
}
