package br.com.neves.bankstatement.dto;

import br.com.neves.bankstatement.jpa.model.Account;
import br.com.neves.bankstatement.jpa.model.AccountStatement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionMessageQueue {
// ------------------------------ FIELDS ------------------------------

    private Long id;
    private BigDecimal amount;
    private String origin;
    private String type;
    private LocalDateTime createdAt;
    private String agency;
    private String number;
    private BigDecimal balance;

    public static Account createAccount(TransactionMessageQueue messageQueue){
        Account account = new Account();
        account.setAgency(messageQueue.getAgency());
        account.setNumber(messageQueue.getNumber());
        account.setBalance(messageQueue.getBalance());

        return account;
    }

    public static AccountStatement createAccountStatement(TransactionMessageQueue messageQueue, Account account){
        AccountStatement accountStatement = new AccountStatement();
        accountStatement.setAccount(account);
        accountStatement.setAmount(messageQueue.getAmount());
        accountStatement.setCreatedAt(messageQueue.getCreatedAt());
        accountStatement.setType(messageQueue.getType());
        accountStatement.setTransactionId(messageQueue.getId());
        accountStatement.setOrigin(messageQueue.getOrigin());

        return accountStatement;
    }
}
