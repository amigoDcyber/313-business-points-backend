CREATE TABLE payouts (
    id UUID PRIMARY KEY,
    transfer_id UUID NOT NULL REFERENCES transfers(id) ON DELETE CASCADE,
    provider VARCHAR(100) NOT NULL,
    provider_ref VARCHAR(255),
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    status VARCHAR(50) NOT NULL,
    failure_reason TEXT,
    processed_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
