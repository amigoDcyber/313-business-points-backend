CREATE TABLE quotes (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    corridor_id UUID NOT NULL REFERENCES corridors(id),
    exchange_rate_id UUID NOT NULL REFERENCES exchange_rates(id),
    amount_sent DECIMAL(15, 2) NOT NULL,
    amount_received DECIMAL(15, 2) NOT NULL,
    fee DECIMAL(15, 2) NOT NULL,
    total_payable DECIMAL(15, 2) NOT NULL,
    send_currency VARCHAR(10) NOT NULL,
    receive_currency VARCHAR(10) NOT NULL,
    status VARCHAR(50) NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
