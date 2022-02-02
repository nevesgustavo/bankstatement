package br.com.neves.bankstatement.service;

import br.com.neves.bankstatement.dto.TransactionMessageQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {
// -------------------------- OTHER METHODS --------------------------

    private final TransactionService transactionService;

    public void action(TransactionMessageQueue message) {
        log.info("The message was received: {}", message.toString());
        transactionService.saveTransaction(message);
    }
}
