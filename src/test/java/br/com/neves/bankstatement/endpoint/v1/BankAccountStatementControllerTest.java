package br.com.neves.bankstatement.endpoint.v1;

import br.com.neves.bankstatement.model.BalanceResponseBody;
import br.com.neves.bankstatement.model.StatementResponseBody;
import br.com.neves.bankstatement.service.TransactionService;
import br.com.neves.bankstatement.utils.ConstantUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
class BankAccountStatementControllerTest {
// ------------------------------ FIELDS ------------------------------

    @InjectMocks
    private BankAccountStatementController bankAccountStatementController;

    @Mock
    private TransactionService transactionService;

// -------------------------- OTHER METHODS --------------------------

    @Test
    @DisplayName("[Unit] getBalance")
    void getBalance() {
        ResponseEntity<BalanceResponseBody> balance = bankAccountStatementController.getBalance(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(balance);
        Assertions.assertEquals(balance.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("[Unit] getHistory")
    void getHistory() {
        ResponseEntity<List<StatementResponseBody>> history = bankAccountStatementController.getHistory(ConstantUtils.AG, ConstantUtils.NUMBER);
        Assertions.assertNotNull(history);
        Assertions.assertEquals(history.getStatusCode(), HttpStatus.OK);
    }

    @BeforeEach
    void setUp() {
        BDDMockito.when(transactionService.getBalanceByAgencyAndNumber(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(new BigDecimal("100"));
    }
}