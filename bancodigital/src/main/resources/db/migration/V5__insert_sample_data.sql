INSERT INTO cliente (id, nome, cpf, email) VALUES
                                               (1, 'João da Silva', '123.456.789-00', 'joao@email.com'),
                                               (2, 'Maria Oliveira', '987.654.321-00', 'maria@email.com');

INSERT INTO conta (numero_conta, saldo, cliente_id) VALUES
                                                        (1001, 250.00, 1),
                                                        (1002, 500.00, 2);

INSERT INTO transacao (numero_conta, forma_pagamento, valor, valor_com_taxa) VALUES
                                                                                 (1001, 'P', 50.00, 50.00),
                                                                                 (1001, 'D', 30.00, 30.90),
                                                                                 (1002, 'C', 100.00, 105.00);
