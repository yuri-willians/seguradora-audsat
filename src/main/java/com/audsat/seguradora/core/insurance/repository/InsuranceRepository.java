package com.audsat.seguradora.core.insurance.repository;

import com.audsat.seguradora.core.insurance.domain.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
