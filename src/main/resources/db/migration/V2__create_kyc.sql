CREATE TABLE kyc_verifications (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    id_type VARCHAR(50) NOT NULL,
    id_number VARCHAR(100) NOT NULL,
    id_document_url VARCHAR(500),
    selfie_url VARCHAR(500),
    proof_of_address_url VARCHAR(500),
    nationality VARCHAR(100),
    occupation VARCHAR(200),
    status VARCHAR(50) NOT NULL,
    rejection_reason TEXT,
    verified_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_kyc_user_id ON kyc_verifications(user_id);
