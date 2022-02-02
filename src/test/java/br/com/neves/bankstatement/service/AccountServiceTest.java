package br.com.neves.bankstatement.service;

import br.com.neves.bankstatement.jpa.model.Account;
import br.com.neves.bankstatement.jpa.model.AccountStatement;
import br.com.neves.bankstatement.jpa.repository.AccountRepository;
import br.com.neves.bankstatement.jpa.repository.AccountStatementRepository;
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

import java.util.List;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountStatementRepository accountStatementRepository;


    @BeforeEach
    void setUp() {
        BDDMockito.when(accountRepository.findByAgencyAndNumber(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(ObjectCreatorUtil.createAccount());

        BDDMockito.when(accountRepository.save(ArgumentMatchers.any(Account.class)))
                .thenReturn(ObjectCreatorUtil.createAccount());

        BDDMockito.when(accountStatementRepository.findByAccount_AgencyAndAccount_Number(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenReturn(Lists.newArrayList(ObjectCreatorUtil.createAccountStatement()));

        BDDMockito.when(accountStatementRepository.save(ArgumentMatchers.any(AccountStatement.class)))
                .thenReturn(ObjectCreatorUtil.createAccountStatement());
    }


    @Test
    @DisplayName("[Unit] - findByAgencyAndNumber")
    void findByAgencyAndNumber() {
        Account account = accountService.findByAgencyAndNumber(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(account);
        Assertions.assertEquals(ConstantUtils.AG, account.getAgency());
        Assertions.assertEquals(ConstantUtils.NUMBER, account.getNumber());
    }

    @Test
    @DisplayName("[Unit] - findStatementsByAccount")
    void findStatementsByAccount() {
        List<AccountStatement> statements = accountService.findStatementsByAccount(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(statements);
        Assertions.assertEquals(statements.size(), 1);
        Assertions.assertEquals(statements.get(0).getAccount().getAgency(), ConstantUtils.AG);
        Assertions.assertEquals(statements.get(0).getAccount().getNumber(), ConstantUtils.NUMBER);
    }

    @Test
    @DisplayName("[Unit] - saveAccount")
    void saveAccount() {
        Account createdAccount = ObjectCreatorUtil.createAccount();
        Account account = accountService.save(ObjectCreatorUtil.createAccount());
        Assertions.assertNotNull(account);
        Assertions.assertEquals(account.getAgency(), createdAccount.getAgency());
        Assertions.assertEquals(account.getNumber(), createdAccount.getNumber());
    }

    @Test
    @DisplayName("[Unit] - saveStatement")
    void saveStatement() {
        AccountStatement statement = ObjectCreatorUtil.createAccountStatement();
        AccountStatement savedStatement = accountService.save(statement);
        Assertions.assertNotNull(savedStatement);
        Assertions.assertEquals(savedStatement.getAccount().getAgency(), statement.getAccount().getAgency());
        Assertions.assertEquals(savedStatement.getAccount().getNumber(), statement.getAccount().getNumber());
    }
}