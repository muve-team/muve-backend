CREATE TABLE category (
                          category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          slug VARCHAR(255) NOT NULL,
                          image_url VARCHAR(255),
                          created_date DATETIME,
                          UNIQUE KEY unique_slug (slug)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE delivery (
                          delivery_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          address VARCHAR(255) NOT NULL,
                          company VARCHAR(255),
                          tracking_number VARCHAR(255),
                          status VARCHAR(50),
                          delivery_date DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE orders (
                        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        status VARCHAR(50) NOT NULL,
                        order_date DATETIME NOT NULL,
                        user_id BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE order_product (
                               order_product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               price BIGINT NOT NULL,
                               count INT NOT NULL,
                               order_id BIGINT NOT NULL,
                               product_id BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE product (
                         product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price BIGINT NOT NULL,
                         stock_quantity INT NOT NULL,
                         image_url VARCHAR(255),
                         created_date DATETIME,
                         updated_date DATETIME,
                         category_id BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE saved (
                       saved_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE saved_product (
                               saved_product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               saved_id BIGINT NOT NULL,
                               product_id BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(20),
                       created_date DATETIME,
                       updated_date DATETIME,
                       city VARCHAR(255),
                       street VARCHAR(255),
                       zipcode VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
