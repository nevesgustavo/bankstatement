package br.com.neves.bankstatement.exception;

public class AccountNotFoundException extends BaseRuntimeException {
// --------------------------- CONSTRUCTORS ---------------------------

    public AccountNotFoundException(String message) {
        super(message);
    }
}
