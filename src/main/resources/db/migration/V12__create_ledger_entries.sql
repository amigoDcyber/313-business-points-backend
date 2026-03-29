CREATE TABLE ledger_entries (
    id UUID PRIMARY KEY,
    transfer_id UUID REFERENCES transfers(id),
    entry_type VARCHAR(50) NOT NULL,
    account_debit VARCHAR(100) NOT NULL,
    account_credit VARCHAR(100) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
