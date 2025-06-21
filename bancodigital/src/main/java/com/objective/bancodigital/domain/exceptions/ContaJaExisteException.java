package com.objective.bancodigital.domain.exceptions;

public class ContaJaExisteException extends RuntimeException {
    public ContaJaExisteException(String message) {
        super(message);
    }
}
