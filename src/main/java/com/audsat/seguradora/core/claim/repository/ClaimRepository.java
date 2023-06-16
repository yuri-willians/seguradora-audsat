package com.audsat.seguradora.core.claim.repository;

import com.audsat.seguradora.core.claim.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(claim.id) > 0 THEN TRUE ELSE FALSE END
            FROM Claim claim
            LEFT JOIN claim.driver driver
            LEFT JOIN driver.customer customer
            WHERE customer.id = :idCustomer
            """)
    Boolean customerHasClaim(Long idCustomer);

    @Query("""
            SELECT CASE WHEN COUNT(claim.id) > 0 THEN TRUE ELSE FALSE END
            FROM Claim claim
            LEFT JOIN claim.car car
            WHERE car.id = :idCar
            """)
    Boolean carHasClaim(Long idCar);

}
