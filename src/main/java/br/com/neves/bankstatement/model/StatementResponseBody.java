package br.com.neves.bankstatement.model;

import br.com.neves.bankstatement.jpa.model.AccountStatement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementResponseBody {
// ------------------------------ FIELDS ------------------------------

    private Long transactionId;
    private BigDecimal amount;
    private String origin;
    private String type;
    private LocalDateTime createdAt;

    public static StatementResponseBody mapFrom(AccountStatement accountStatement){
        StatementResponseBody statementResponseBody = new StatementResponseBody();
        statementResponseBody.setTransactionId(accountStatement.getTransactionId());
        statementResponseBody.setAmount(accountStatement.getAmount());
        statementResponseBody.setOrigin(accountStatement.getOrigin());
        statementResponseBody.setType(accountStatement.getType());
        statementResponseBody.setCreatedAt(accountStatement.getCreatedAt());

        return statementResponseBody;
    }

    public static LinkedList<StatementResponseBody> mapFrom(List<AccountStatement> accountStatements){
        LinkedList<StatementResponseBody> statements = new LinkedList<>();
        for (AccountStatement accountStatement : accountStatements) {
            statements.add(mapFrom(accountStatement));
        }

        return statements;
    }
}
