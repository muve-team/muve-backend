ALTER TABLE orders
    ADD COLUMN orderer_name VARCHAR(255) NOT NULL,
    ADD COLUMN orderer_phone_number VARCHAR(255) NOT NULL,
    ADD COLUMN orderer_email VARCHAR(255) NOT NULL,
    ADD COLUMN delivery_id BIGINT NOT NULL