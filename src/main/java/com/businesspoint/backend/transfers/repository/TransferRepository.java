package com.businesspoint.backend.transfers.repository;

import com.businesspoint.backend.common.enums.TransferStatus;
import com.businesspoint.backend.transfers.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    List<Transfer> findAllByUserId(UUID userId);
    List<Transfer> findAllByStatus(TransferStatus status);
    Optional<Transfer> findByReferenceCode(String referenceCode);
}
