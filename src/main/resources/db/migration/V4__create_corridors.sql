CREATE TABLE corridors (
    id UUID PRIMARY KEY,
    from_country VARCHAR(100) NOT NULL,
    to_country VARCHAR(100) NOT NULL,
    from_currency VARCHAR(10) NOT NULL,
    to_currency VARCHAR(10) NOT NULL,
    delivery_method VARCHAR(50) NOT NULL,
    delivery_estimate VARCHAR(100),
    min_amount DECIMAL(15, 2),
    max_amount DECIMAL(15, 2),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
