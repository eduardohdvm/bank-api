CREATE TABLE transacao (
                           id SERIAL PRIMARY KEY,
                           numero_conta BIGINT NOT NULL,
                           forma_pagamento CHAR(1) NOT NULL,
                           valor DECIMAL(19,2) NOT NULL,
                           valor_com_taxa DECIMAL(19,2) NOT NULL,
                           data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (numero_conta) REFERENCES conta(numero_conta)
);
