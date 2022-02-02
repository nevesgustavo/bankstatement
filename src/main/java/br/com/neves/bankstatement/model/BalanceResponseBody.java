package br.com.neves.bankstatement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceResponseBody {
// ------------------------------ FIELDS ------------------------------

    private String agency;
    private String number;
    private BigDecimal balance;
}
