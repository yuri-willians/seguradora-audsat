package com.audsat.seguradora.core.car.service;

import com.audsat.seguradora.core.car.domain.Car;
import com.audsat.seguradora.core.car.domain.CarBuilder;
import com.audsat.seguradora.core.car.repository.CarRepository;
import com.audsat.seguradora.core.commons.exception.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("car")})
@DisplayName("Test service GetCarById")
@ExtendWith(MockitoExtension.class)
class GetCarByIdTest {

    private static final long ID_CAR = 1L;

    private static final String CAR_NOT_FOUND = "Car not found.";

    @Mock
    private CarRepository repository;

    private GetCarById sut;

    private static Car buildCar() {
        return CarBuilder.aCar()
                .withId(ID_CAR)
                .build();
    }

    @BeforeEach
    void setUp() {
        this.sut = new GetCarById(this.repository);
    }

    @Test
    @DisplayName("Must return car")
    void test1() {
        final var car = buildCar();

        Mockito.when(this.repository.findById(ID_CAR))
                .thenReturn(Optional.of(car));

        final var response = this.sut.execute(ID_CAR);
        Assertions.assertEquals(ID_CAR, response.getId());
        Mockito.verify(this.repository, times(1)).findById(ID_CAR);
    }

    @Test
    @DisplayName("Must throw exception")
    void test2() {
        Mockito.when(this.repository.findById(ID_CAR))
                .thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(EntityNotFoundException.class, () -> this.sut.execute(ID_CAR));
        Assertions.assertEquals(CAR_NOT_FOUND, exception.getMessage());
        Mockito.verify(this.repository, times(1)).findById(ID_CAR);
    }
}
