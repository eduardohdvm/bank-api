CREATE TABLE cliente (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         cpf VARCHAR(14) UNIQUE NOT NULL,
                         email VARCHAR(100)
);

