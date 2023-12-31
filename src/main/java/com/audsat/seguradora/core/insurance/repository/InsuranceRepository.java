package com.audsat.seguradora.core.insurance.repository;

import com.audsat.seguradora.core.insurance.domain.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(insurance.id) > 0 THEN TRUE ELSE FALSE END
            FROM Insurance insurance
            LEFT JOIN insurance.customer customer
            LEFT JOIN customer.driver driver
            WHERE TIMESTAMPDIFF(YEAR, driver.birthdate, CURRENT_DATE()) BETWEEN :minAge AND :maxAge
            AND insurance.id = :idInsurance
            """)
    Boolean isCustomerBetweenAgePeriod(Long idInsurance, Long minAge, Long maxAge);
}
