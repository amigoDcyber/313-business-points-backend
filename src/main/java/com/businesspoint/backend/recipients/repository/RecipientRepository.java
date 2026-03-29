package com.businesspoint.backend.recipients.repository;

import com.businesspoint.backend.recipients.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    List<Recipient> findAllByUserId(UUID userId);
}
