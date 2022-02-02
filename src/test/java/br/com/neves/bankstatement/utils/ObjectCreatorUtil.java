package br.com.neves.bankstatement.utils;

import br.com.neves.bankstatement.dto.TransactionMessageQueue;
import br.com.neves.bankstatement.jpa.model.Account;
import br.com.neves.bankstatement.jpa.model.AccountStatement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ObjectCreatorUtil {

    public static Account createAccount(){
        Account account = new Account();
        account.setBalance(new BigDecimal("100"));
        account.setNumber("0087524-5");
        account.setAgency("1133-9");

        return account;
    }

    public static AccountStatement createAccountStatement(){
        AccountStatement accountStatement = new AccountStatement();
        accountStatement.setOrigin("Tests");
        accountStatement.setType("DEBIT");
        accountStatement.setCreatedAt(LocalDateTime.now());
        accountStatement.setAccount(createAccount());
        accountStatement.setAmount(new BigDecimal("10"));
        accountStatement.setTransactionId(1L);

        return accountStatement;
    }


    public static TransactionMessageQueue createTransactionMessageQueue(){
        TransactionMessageQueue transactionMessageQueue = new TransactionMessageQueue();
        transactionMessageQueue.setAgency(ConstantUtils.AG);
        transactionMessageQueue.setAmount(new BigDecimal("12"));
        transactionMessageQueue.setBalance(new BigDecimal("100"));
        transactionMessageQueue.setOrigin("tests");
        transactionMessageQueue.setId(1L);
        transactionMessageQueue.setType("DEBIT");
        transactionMessageQueue.setCreatedAt(LocalDateTime.now());
        transactionMessageQueue.setNumber(ConstantUtils.NUMBER);

        return transactionMessageQueue;
    }
}
