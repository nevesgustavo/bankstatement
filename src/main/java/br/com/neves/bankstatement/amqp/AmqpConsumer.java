package br.com.neves.bankstatement.amqp;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
