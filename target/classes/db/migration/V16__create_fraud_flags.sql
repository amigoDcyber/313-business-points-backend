CREATE TABLE fraud_flags (
    id UUID PRIMARY KEY,
    transfer_id UUID NOT NULL REFERENCES transfers(id),
    user_id UUID NOT NULL REFERENCES users(id),
    rule_triggered VARCHAR(255) NOT NULL,
    risk_level VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    resolution TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
