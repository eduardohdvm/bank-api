# Banco Digital API

## Visão Geral
Este projeto é uma API RESTful para um sistema de banco digital, que permite a criação de contas
e a realização de transações com diferentes formas de pagamento.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- H2 (Banco de dados em memória para testes)
- PostgreSQL (Banco de dados principal)
- JUnit 5 (Testes)
- ModelMapper
- Lombok
  
## Endpoints

### Criar Conta
`POST /conta`
```json
{
 "numeroConta": 123456,
 "saldo": 100.00
}
```
### Buscar Conta
`GET /conta/{numeroConta}`
### Realizar Transação
`POST /transacao`
```json
{
 "numeroConta": 123456,
 "valor": 50.00,
 "formaPagamento": "C" // C = Crédito, D = Débito, P = Pix
}
```
## Estratégias de Pagamento
- **PixStrategy**: Sem taxa.
- **DebitoStrategy**: 3% de taxa.
- **CreditoStrategy**: 5% de taxa.
  
## Execução Local
```bash
mvn clean install
mvn spring-boot:run
```

## Testes
Executar com:
```bash
mvn test
```
## Observações
- API expõe as operações básicas de um banco com controle de saldo e validações específicas
para transações com saldo insuficiente.
- Testes cobrem tanto o serviço quanto o comportamento com repositório real (banco em memória).
