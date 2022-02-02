package br.com.neves.bankstatement.integration.controller;

import br.com.neves.bankstatement.BaseContextAwareTest;
import br.com.neves.bankstatement.dto.TransactionMessageQueue;
import br.com.neves.bankstatement.jpa.model.AccountStatement;
import br.com.neves.bankstatement.service.AccountService;
import br.com.neves.bankstatement.service.TransactionService;
import br.com.neves.bankstatement.utils.ConstantUtils;
import br.com.neves.bankstatement.utils.ObjectCreatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class BankAccountStatementIntegrationControllerTest extends BaseContextAwareTest {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

// -------------------------- OTHER METHODS --------------------------

    @Test
    @DisplayName("[Integration] getBalance")
    void getBalance() throws Exception {
        mockMvc.perform(get("/api/v1/account/" + ConstantUtils.AG + "/" + ConstantUtils.NUMBER + "/balance"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.agency").value(ConstantUtils.AG))
                .andExpect(jsonPath("$.number").value(ConstantUtils.NUMBER));
    }

    @Test
    @DisplayName("[Integration] getHistory")
    void getHistory() throws Exception {
        TransactionMessageQueue messageQueue = ObjectCreatorUtil.createTransactionMessageQueue();
        transactionService.saveTransaction(messageQueue);


        mockMvc.perform(get("/api/v1/account/" + ConstantUtils.AG + "/" + ConstantUtils.NUMBER + "/statement"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount").value(messageQueue.getAmount()));
    }

    @BeforeEach
    void setUp() {
        List<AccountStatement> account = accountService.findStatementsByAccount(ConstantUtils.AG, ConstantUtils.NUMBER);
        if (account == null)
            accountService.save(ObjectCreatorUtil.createAccount());
    }
}
