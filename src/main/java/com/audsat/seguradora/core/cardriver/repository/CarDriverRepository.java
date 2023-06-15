package com.audsat.seguradora.core.cardriver.repository;

import com.audsat.seguradora.core.cardriver.domain.CarDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriver, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(cardriver.id) > 0 THEN TRUE ELSE FALSE END
            FROM CarDriver cardriver
            LEFT JOIN cardriver.car car
            LEFT JOIN cardriver.driver driver
            LEFT JOIN driver.customer customer
            WHERE customer.id = :idCustomer
            AND car.id = :idCar
            AND cardriver.isMainDriver = true
            """)
    Boolean existsMainCarDriverByCustomerAndCar(Long idCustomer, Long idCar);

}
