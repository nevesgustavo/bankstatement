package br.com.neves.bankstatement.jpa.repository;

import br.com.neves.bankstatement.jpa.model.AccountStatement;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountStatementRepository extends MongoRepository<AccountStatement, String> {
// -------------------------- OTHER METHODS --------------------------

    List<AccountStatement> findByAccount_AgencyAndAccount_Number(String agency, String number, Sort sort);
}
