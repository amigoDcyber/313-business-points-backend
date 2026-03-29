package com.businesspoint.backend.fraud.repository;

import com.businesspoint.backend.fraud.entity.FraudFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FraudFlagRepository extends JpaRepository<FraudFlag, UUID> {
    List<FraudFlag> findAllByTransferId(UUID transferId);
}
