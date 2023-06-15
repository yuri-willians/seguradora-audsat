package com.audsat.seguradora.core.driver.repository;

import com.audsat.seguradora.core.driver.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
