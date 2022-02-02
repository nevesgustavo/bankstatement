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

import java.math.BigDecimal;
import java.util.List;

public class TransactionIntegrationServiceTest extends BaseContextAwareTest {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

// -------------------------- OTHER METHODS --------------------------

    @Test
    @DisplayName("[Integration] - findStatementByAgencyAndNumber")
    void findStatementByAgencyAndNumber() {
        saveTransaction();
        List<AccountStatement> statements = transactionService.findStatementByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(statements);
        Assertions.assertEquals(statements.get(0).getAccount().getAgency(), ConstantUtils.AG);
        Assertions.assertEquals(statements.get(0).getAccount().getNumber(), ConstantUtils.NUMBER);
    }

    @Test
    @DisplayName("[Integration] - getBalanceByAgencyAndNumber")
    void getBalanceByAgencyAndNumber() {
        saveTransaction();
        BigDecimal balance = transactionService.getBalanceByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(balance);
        Assertions.assertEquals(balance, ObjectCreatorUtil.createAccount().getBalance());
    }

    @Test
    @DisplayName("[Integration] - saveTransaction")
    void saveTransaction() {
        TransactionMessageQueue queue = ObjectCreatorUtil.createTransactionMessageQueue();
        transactionService.saveTransaction(ObjectCreatorUtil.createTransactionMessageQueue());
        Assertions.assertEquals(transactionService.getBalanceByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER), queue.getBalance());
    }

    @BeforeEach
    void setUp() {
        Account account = accountService.findByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        if (account == null)
            accountService.save(ObjectCreatorUtil.createAccount());
    }
}
