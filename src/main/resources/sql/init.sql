CREATE DATABASE usersapi_adm;

USE usersapi_adm;

CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP
);

INSERT INTO users (id, name, password, email, createdAt, updatedAt) VALUES
    ('a5d7c836-6b8b-4c2c-bd9d-0a5d786de5a1', 'John Doe', 'password123', 'john@example.com', '2023-01-01 12:00:00', '2023-01-01 12:00:00'),
    ('2f3616eb-232b-4a91-b0ff-51bfb2740a55', 'Jane Smith', 'p@ssw0rd', 'jane@example.com', '2023-01-02 14:30:00', '2023-01-02 14:30:00'),
    ('1e84be45-0d59-4d9b-a82e-149c4c50df22', 'Alice Johnson', 'secure123', 'alice@example.com', '2023-01-03 10:15:00', '2023-01-03 10:15:00'),
    ('e507c854-83cb-483b-9b04-dbd2e7b20e33', 'Bob Williams', 'testpass', 'bob@example.com', '2023-01-04 08:45:00', '2023-01-04 08:45:00'),
    ('b8a77f60-917d-4c6a-9c25-9bce16c2f1c1', 'Eve Anderson', 'pass123', 'eve@example.com', '2023-01-05 16:20:00', '2023-01-05 16:20:00');
