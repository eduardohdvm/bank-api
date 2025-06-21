ALTER TABLE conta ADD COLUMN cliente_id BIGINT;
ALTER TABLE conta ADD CONSTRAINT fk_conta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id);
