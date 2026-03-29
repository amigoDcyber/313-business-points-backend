package com.businesspoint.backend.ledger.service;

import com.businesspoint.backend.ledger.entity.LedgerEntry;
import com.businesspoint.backend.ledger.repository.LedgerEntryRepository;
import com.businesspoint.backend.transfers.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerEntryRepository ledgerRepository;

    @Transactional
    public void recordTransferPayment(Transfer transfer, BigDecimal amount) {
        // Record the user paying the platform (Debit User Funding, Credit Platform Escrow)
        LedgerEntry debitUser = LedgerEntry.builder()
                .transfer(transfer)
                .entryType("PAYMENT_COLLECTION")
                .accountDebit("USER_WALLET_" + transfer.getUser().getId())
                .accountCredit("BP_ESCROW_ACCOUNT")
                .amount(amount)
                .currency(transfer.getSendCurrency())
                .description("Funds collected from user for transfer")
                .build();
                
        ledgerRepository.save(debitUser);
    }

    @Transactional
    public void recordTransferPayout(Transfer transfer, BigDecimal amountPayout) {
        // Record the platform paying the recipient (Debit Platform Payout, Credit Recipient)
        LedgerEntry creditRecipient = LedgerEntry.builder()
                .transfer(transfer)
                .entryType("PAYOUT_EXECUTION")
                .accountDebit("BP_PAYOUT_POOL_" + transfer.getReceiveCurrency())
                .accountCredit("RECIPIENT_WALLET_" + transfer.getRecipient().getId())
                .amount(amountPayout)
                .currency(transfer.getReceiveCurrency())
                .description("Funds paid out to recipient")
                .build();

        ledgerRepository.save(creditRecipient);
    }
    
    @Transactional
    public void recordFeeCollection(Transfer transfer, BigDecimal feeAmount) {
        LedgerEntry feeEntry = LedgerEntry.builder()
                .transfer(transfer)
                .entryType("FEE_COLLECTION")
                .accountDebit("BP_ESCROW_ACCOUNT")
                .accountCredit("BP_REVENUE_ACCOUNT")
                .amount(feeAmount)
                .currency(transfer.getSendCurrency())
                .description("Platform fee collection")
                .build();
                
        ledgerRepository.save(feeEntry);
    }

    @Transactional(readOnly = true)
    public List<LedgerEntry> getEntriesForTransfer(UUID transferId) {
        return ledgerRepository.findAllByTransferId(transferId);
    }
}
