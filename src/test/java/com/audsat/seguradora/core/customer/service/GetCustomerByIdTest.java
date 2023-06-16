package com.audsat.seguradora.core.customer.service;

import com.audsat.seguradora.core.commons.exception.EntityNotFoundException;
import com.audsat.seguradora.core.customer.domain.Customer;
import com.audsat.seguradora.core.customer.domain.CustomerBuilder;
import com.audsat.seguradora.core.customer.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;

@Tags({@Tag("unit"), @Tag("customer")})
@DisplayName("Test service GetCustomerById")
@ExtendWith(MockitoExtension.class)
class GetCustomerByIdTest {

    private static final long ID_CUSTOMER = 1L;

    private static final String CUSTOMER_NOT_FOUND = "Customer not found.";

    @Mock
    private CustomerRepository repository;

    private GetCustomerById sut;

    private static Customer buildCustomer() {
        return CustomerBuilder.aCustomer()
                .withId(ID_CUSTOMER)
                .build();
    }

    @BeforeEach
    void setUp() {
        this.sut = new GetCustomerById(this.repository);
    }

    @Test
    @DisplayName("Must return customer")
    void test1() {
        final var customer = buildCustomer();

        Mockito.when(this.repository.findById(ID_CUSTOMER))
                .thenReturn(Optional.of(customer));

        final var response = this.sut.execute(ID_CUSTOMER);
        Assertions.assertEquals(ID_CUSTOMER, response.getId());
        Mockito.verify(this.repository, times(1))
                .findById(ID_CUSTOMER);
    }

    @Test
    @DisplayName("Must throw exception")
    void test2() {
        Mockito.when(this.repository.findById(ID_CUSTOMER))
                .thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(EntityNotFoundException.class, () -> this.sut.execute(ID_CUSTOMER));
        Assertions.assertEquals(CUSTOMER_NOT_FOUND, exception.getMessage());
        Mockito.verify(this.repository, times(1))
                .findById(ID_CUSTOMER);
    }
}
