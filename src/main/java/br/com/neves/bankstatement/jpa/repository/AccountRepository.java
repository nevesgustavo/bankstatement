package br.com.neves.bankstatement.jpa.repository;

import br.com.neves.bankstatement.jpa.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
// -------------------------- OTHER METHODS --------------------------

    Account findByAgencyAndNumber(String agency, String number);
}
