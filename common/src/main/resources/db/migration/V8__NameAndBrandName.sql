ALTER TABLE product
    ADD COLUMN korean_name VARCHAR(255) NOT NULL AFTER name,
    ADD COLUMN english_name VARCHAR(255) NOT NULL AFTER korean_name,
    ADD COLUMN brand_korean_name VARCHAR(255) NOT NULL AFTER english_name,
    ADD COLUMN brand_english_name VARCHAR(255) NOT NULL AFTER brand_korean_name;

ALTER TABLE product
    DROP COLUMN name,
    DROP COLUMN brand_name;