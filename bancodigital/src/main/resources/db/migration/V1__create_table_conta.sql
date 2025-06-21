CREATE TABLE conta (
                       numero_conta BIGINT PRIMARY KEY,
                       saldo DECIMAL(19,2) NOT NULL,
                       cliente_id INT,
                       FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);