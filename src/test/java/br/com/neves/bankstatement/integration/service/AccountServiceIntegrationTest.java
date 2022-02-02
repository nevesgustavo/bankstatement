package br.com.neves.bankstatement.integration.service;

import br.com.neves.bankstatement.BaseContextAwareTest;
import br.com.neves.bankstatement.dto.TransactionMessageQueue;
import br.com.neves.bankstatement.jpa.model.Account;
import br.com.neves.bankstatement.jpa.model.AccountStatement;
import br.com.neves.bankstatement.service.AccountService;
import br.com.neves.bankstatement.service.TransactionService;
import br.com.neves.bankstatement.utils.ConstantUtils;
import br.com.neves.bankstatement.utils.ObjectCreatorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceIntegrationTest extends BaseContextAwareTest {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

// -------------------------- OTHER METHODS --------------------------

    @Test
    @DisplayName("[Integration] - findByAgencyAndNumber")
    void findByAgencyAndNumber() {
        Account account = accountService.findByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(account);
        Assertions.assertEquals(ConstantUtils.AG, account.getAgency());
        Assertions.assertEquals(ConstantUtils.NUMBER, account.getNumber());
    }

    @Test
    @DisplayName("[Integration] - findStatementsByAccount")
    void findStatementsByAccount() {
        TransactionMessageQueue transactionMessageQueue = ObjectCreatorUtil.createTransactionMessageQueue();
        transactionService.saveTransaction(transactionMessageQueue);

        List<AccountStatement> statements = accountService.findStatementsByAccount(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(statements);
        Assertions.assertEquals(statements.get(0).getAccount().getAgency(), ConstantUtils.AG);
        Assertions.assertEquals(statements.get(0).getAccount().getNumber(), ConstantUtils.NUMBER);
        Assertions.assertEquals(statements.get(0).getAccount().getBalance(), transactionMessageQueue.getBalance());
    }

    @Test
    @DisplayName("[Integration] - saveAccount")
    void saveAccount() {
        Account createdAccount = ObjectCreatorUtil.createAccount();
        createdAccount.setAgency("111");
        createdAccount.setNumber("222");
        Account account = accountService.save(createdAccount);
        Assertions.assertNotNull(account);
        Assertions.assertEquals(account.getAgency(), createdAccount.getAgency());
        Assertions.assertEquals(account.getNumber(), createdAccount.getNumber());
    }

    @Test
    @DisplayName("[Integration] - saveStatement")
    void saveStatement() {
        AccountStatement statement = ObjectCreatorUtil.createAccountStatement();
        statement.setAccount(accountService.findByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER));
        AccountStatement savedStatement = accountService.save(statement);
        Assertions.assertNotNull(savedStatement);
        Assertions.assertEquals(savedStatement.getAccount().getAgency(), statement.getAccount().getAgency());
        Assertions.assertEquals(savedStatement.getAccount().getNumber(), statement.getAccount().getNumber());
    }

    @BeforeEach
    void setUp() {
        Account account = accountService.findByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        if (account == null)
            accountService.save(ObjectCreatorUtil.createAccount());
    }
}
