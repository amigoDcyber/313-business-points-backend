package com.businesspoint.backend.quotes.repository;

import com.businesspoint.backend.quotes.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {
    List<Quote> findAllByUserId(UUID userId);
}
