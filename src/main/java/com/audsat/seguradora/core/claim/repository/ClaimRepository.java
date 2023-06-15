package com.audsat.seguradora.core.claim.repository;

import com.audsat.seguradora.core.claim.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
