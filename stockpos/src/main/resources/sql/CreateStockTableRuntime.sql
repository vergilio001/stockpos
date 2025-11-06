DROP TABLE IF EXISTS stock_pos;
CREATE TABLE stock_pos (
    id NUMBER PRIMARY KEY AUTO_INCREMENT,
    sku VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    stock_quantity NUMBER
);