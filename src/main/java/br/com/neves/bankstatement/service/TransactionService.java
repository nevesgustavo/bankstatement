package br.com.neves.bankstatement.service;

import br.com.neves.bankstatement.dto.TransactionMessageQueue;
import br.com.neves.bankstatement.exception.AccountNotFoundException;
import br.com.neves.bankstatement.jpa.model.Account;
import br.com.neves.bankstatement.jpa.model.AccountStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
// ------------------------------ FIELDS ------------------------------

    private final AccountService accountService;

// -------------------------- OTHER METHODS --------------------------

    public void saveTransaction(TransactionMessageQueue messageQueue) {
        Account account = accountService.findByAgencyAndNumber(messageQueue.getAgency(), messageQueue.getNumber());
        if (account == null)
            account = TransactionMessageQueue.createAccount(messageQueue);

        account.setBalance(messageQueue.getBalance());
        accountService.save(account);

        accountService.save(TransactionMessageQueue.createAccountStatement(messageQueue, account));
    }

    public BigDecimal getBalanceByAgencyAndNumber(String agency, String number) {
        Account account = accountService.findByAgencyAndNumber(agency, number);

        if (account == null)
            throw new AccountNotFoundException("The account was not found");

        return account.getBalance();
    }

    public List<AccountStatement> findStatementByAgencyAndNumber(String agency, String number){
        return accountService.findStatementsByAccount(agency, number);
    }
}
