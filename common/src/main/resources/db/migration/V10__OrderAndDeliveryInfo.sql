ALTER TABLE orders
    ADD COLUMN receiver_name VARCHAR(255) NOT NULL,
    ADD COLUMN receiver_phone_number VARCHAR(255) NOT NULL,
    ADD COLUMN payment_method VARCHAR(255) NOT NULL;

ALTER TABLE delivery
    ADD COLUMN delivery_request VARCHAR(255) NULL;