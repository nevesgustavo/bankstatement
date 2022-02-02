package br.com.neves.bankstatement.exception;

public abstract class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String message) {
        super(message);
    }
}
