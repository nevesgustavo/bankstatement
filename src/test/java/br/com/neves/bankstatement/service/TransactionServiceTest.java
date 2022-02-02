package br.com.neves.bankstatement.service;

import br.com.neves.bankstatement.jpa.model.Account;
import br.com.neves.bankstatement.jpa.model.AccountStatement;
import br.com.neves.bankstatement.utils.ConstantUtils;
import br.com.neves.bankstatement.utils.ObjectCreatorUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp(){
        BDDMockito.when(accountService.findByAgencyAndNumber(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(ObjectCreatorUtil.createAccount());

        BDDMockito.when(accountService.save(ArgumentMatchers.any(Account.class)))
                .thenReturn(ObjectCreatorUtil.createAccount());

        BDDMockito.when(accountService.findStatementsByAccount(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Lists.newArrayList(ObjectCreatorUtil.createAccountStatement()));

        BDDMockito.when(accountService.save(ArgumentMatchers.any(Account.class)))
                .thenReturn(ObjectCreatorUtil.createAccount());
    }

    @Test
    @DisplayName("[Unit] - saveTransaction")
    void saveTransaction() {
        transactionService.saveTransaction(ObjectCreatorUtil.createTransactionMessageQueue());
    }

    @Test
    @DisplayName("[Unit] - getBalanceByAgencyAndNumber")
    void getBalanceByAgencyAndNumber() {
        BigDecimal balance = transactionService.getBalanceByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(balance);
        Assertions.assertEquals(balance, ObjectCreatorUtil.createAccount().getBalance());
    }

    @Test
    @DisplayName("[Unit] - findStatementByAgencyAndNumber")
    void findStatementByAgencyAndNumber() {
        List<AccountStatement> statements = transactionService.findStatementByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(statements);
        Assertions.assertEquals(statements.size(), 1);
        Assertions.assertEquals(statements.get(0).getAccount().getAgency(), ConstantUtils.AG);
        Assertions.assertEquals(statements.get(0).getAccount().getNumber(), ConstantUtils.NUMBER);

    }
}