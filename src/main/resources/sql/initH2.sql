CREATE SCHEMA IF NOT EXISTS usersapi;

SET SCHEMA usersapi;

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    role ENUM('ADMIN', 'MODERATOR', 'USER') NOT NULL DEFAULT 'USER',
    created_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_At TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

MERGE INTO users AS target
USING (VALUES
    ('a5d7c836-6b8b-4c2c-bd9d-0a5d786de5a1', 'John Doe', 'password123', 'john@example.com', TRUE, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('2f3616eb-232b-4a91-b0ff-51bfb2740a55', 'Jane Smith', 'p@ssw0rd', 'jane@example.com', FALSE, 'MODERATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('1e84be45-0d59-4d9b-a82e-149c4c50df22', 'Alice Johnson', 'secure123', 'alice@example.com', TRUE, 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('e507c854-83cb-483b-9b04-dbd2e7b20e33', 'Bob Williams', 'testpass', 'bob@example.com', TRUE, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('b8a77f60-917d-4c6a-9c25-9bce16c2f1c1', 'Eve Anderson', 'pass123', 'eve@example.com', TRUE, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
) AS source (id, name, password, email, active, role, created_At, updated_At)
ON target.id = source.id
WHEN NOT MATCHED THEN
    INSERT (id, name, password, email, active, role, created_At, updated_At)
    VALUES (source.id, source.name, source.password, source.email, source.active, source.role, source.created_At, source.updated_At);

