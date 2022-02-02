package br.com.neves.bankstatement.endpoint.v1;

import br.com.neves.bankstatement.jpa.model.AccountStatement;
import br.com.neves.bankstatement.model.BalanceResponseBody;
import br.com.neves.bankstatement.model.StatementResponseBody;
import br.com.neves.bankstatement.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "BankAccountStatement", description = "Apis to show account history and balance")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class BankAccountStatementController {
// ------------------------------ FIELDS ------------------------------

    private final TransactionService transactionService;

// -------------------------- OTHER METHODS --------------------------

    @GetMapping("/{agency}/{number}/balance")
    @Operation(summary = "Return the balance")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the account", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = BalanceResponseBody.class))})}
    )
    public ResponseEntity<BalanceResponseBody> getBalance(@PathVariable String agency, @PathVariable String number) {
        return ResponseEntity.ok(new BalanceResponseBody(agency, number, transactionService.getBalanceByAgencyAndNumber(agency, number)));
    }

    @GetMapping("/{agency}/{number}/statement")
    @Operation(summary = "Return the history")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the account", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = StatementResponseBody.class))})}
    )
    public ResponseEntity<List<StatementResponseBody>> getHistory(@PathVariable String agency, @PathVariable String number) {
        List<AccountStatement> statements = transactionService.findStatementByAgencyAndNumber(agency, number);
        return ResponseEntity.ok(StatementResponseBody.mapFrom(statements));
    }
}
