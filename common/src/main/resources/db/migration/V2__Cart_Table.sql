ALTER TABLE product
ADD COLUMN cart_id BIGINT;

CREATE TABLE cart (
                      cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE cart_product (
                              cart_product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              cart_id BIGINT NOT NULL,
                              product_id BIGINT NOT NULL,
                              count INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
