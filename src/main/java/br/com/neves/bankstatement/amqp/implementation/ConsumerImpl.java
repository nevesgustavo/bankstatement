package br.com.neves.bankstatement.amqp.implementation;

import br.com.neves.bankstatement.amqp.AmqpConsumer;
import br.com.neves.bankstatement.dto.TransactionMessageQueue;
import br.com.neves.bankstatement.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerImpl implements AmqpConsumer<TransactionMessageQueue> {
// ------------------------------ FIELDS ------------------------------

    private final ConsumerService consumerService;

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface AmqpConsumer ---------------------

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(TransactionMessageQueue message) {
        try {
            consumerService.action(message);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
