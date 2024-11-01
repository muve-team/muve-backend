ALTER TABLE product
ADD COLUMN parent_id BIGINT;

CREATE TABLE time_deal (
                      cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id BIGINT NOT NULL,
                      start_at DATETIME,
                      end_at DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
