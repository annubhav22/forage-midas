package com.jpmc.midascore.kafka;

import com.jpmc.midascore.foundation.Ledger;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.foundation.TransactionParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumerService {

    @Autowired
    private Ledger ledger;

    @KafkaListener(topics = "transactions", groupId = "midas-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        System.out.println("📨 Consumed message from Kafka: " + message);

        // Parse transactions from message
        List<Transaction> transactions = TransactionParser.parseTransactions(message);

        // Process transactions
        for (Transaction tx : transactions) {
            ledger.processTransaction(tx);
        }

        System.out.println("✅ All transactions processed by consumer.");
    }
}
