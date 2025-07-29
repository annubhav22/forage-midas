package com.jpmc.midascore;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import com.jpmc.midascore.foundation.Ledger;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092", "port=9092"
})
public class TaskFourTests {

    private static final Logger logger = LoggerFactory.getLogger(TaskFourTests.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private FileLoader fileLoader;

    @Autowired
    private Ledger ledger;

    @Test
    void task_four_verifier() throws InterruptedException {
        logger.info("🚀 Starting Task 4 verifier test...");

        // Step 1: Populate initial users
        userPopulator.populate();
        logger.info("✅ Users populated.");

        // Step 2: Load test transactions from file
        String[] transactionLines = readFile("D:\\forage-midas\\src\\test\\resources\\test_data\\kjhgfdsaj.hjkl");

        if (transactionLines == null) {
            logger.error("❌ Transaction file couldn't be read. Please check the file path or content.");
            return;
        }

        // Step 3: Send transactions to Kafka
        for (String transactionLine : transactionLines) {
            kafkaProducer.send(transactionLine);
        }
        logger.info("✅ All transactions sent to Kafka.");

        // Step 4: Allow time for processing
        Thread.sleep(2000);

        // Step 5: Check Wilbur’s balance
        float wilburBalance = ledger.getBalance("wilbur");
        logger.info("✅ Wilbur's balance: " + wilburBalance);

        logger.info("----------------------------------------------------------");
        logger.info("📌 Use your debugger to inspect the balance of Wilbur.");
        logger.info("You may stop the test once you've verified the result.");

        // Step 6: Keep running for manual inspection (can be stopped manually)
        while (true) {
            Thread.sleep(20000);
            logger.info("🕵️ Still running for inspection...");
        }
    }

    // Utility method to safely read a file
    private String[] readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath)).toArray(new String[0]);
        } catch (Exception e) {
            logger.error("❌ Failed to read transaction file: {}", filePath, e);
            return null;
        }
    }
    
}
