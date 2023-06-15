package com.audsat.seguradora.core.cardriver.repository;

import com.audsat.seguradora.core.cardriver.domain.CarDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriver, Long> {
}
