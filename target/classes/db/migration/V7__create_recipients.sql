CREATE TABLE recipients (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    country VARCHAR(100) NOT NULL,
    delivery_method VARCHAR(50) NOT NULL,
    bank_name VARCHAR(200),
    account_number VARCHAR(100),
    wallet_type VARCHAR(100),
    network_operator VARCHAR(100),
    is_favorite BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
