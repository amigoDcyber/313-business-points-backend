package com.businesspoint.backend.transfers.repository;

import com.businesspoint.backend.transfers.entity.TransferStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferStatusLogRepository extends JpaRepository<TransferStatusLog, UUID> {
    List<TransferStatusLog> findAllByTransferIdOrderByCreatedAtDesc(UUID transferId);
}
