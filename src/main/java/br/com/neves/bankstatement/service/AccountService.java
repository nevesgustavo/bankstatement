package br.com.neves.bankstatement.service;

import br.com.neves.bankstatement.jpa.model.Account;
import br.com.neves.bankstatement.jpa.model.AccountStatement;
import br.com.neves.bankstatement.jpa.repository.AccountRepository;
import br.com.neves.bankstatement.jpa.repository.AccountStatementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
// ------------------------------ FIELDS ------------------------------

    private final AccountRepository accountRepository;
    private final AccountStatementRepository accountStatementRepository;

// -------------------------- OTHER METHODS --------------------------

    public Account findByAgencyAndNumber(String agency, String number) {
        return accountRepository.findByAgencyAndNumber(agency, number);
    }

    public List<AccountStatement> findStatementsByAccount(String agency, String number) {
        return accountStatementRepository.findByAccount_AgencyAndAccount_Number(agency, number, Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public AccountStatement save(AccountStatement accountStatement) {
        return accountStatementRepository.save(accountStatement);
    }
}
