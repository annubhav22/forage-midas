package com.jpmc.midascore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.entity.UserRecord;

@Component
public class UserPopulator {

    private static final Logger logger = LoggerFactory.getLogger(UserPopulator.class);

    @Autowired
    private FileLoader fileLoader;

    @Autowired
    private DatabaseConduit databaseConduit;

    public void populate() {
        String[] userLines = fileLoader.loadStrings("/test_data/lkjhgfdsa.hjkl");

        if (userLines == null || userLines.length == 0) {
            logger.error("❌ userLines is null or empty. Make sure the file exists and is not empty.");
            throw new IllegalStateException("userLines is null or empty");
        }

        for (String userLine : userLines) {
            try {
                String[] userData = userLine.split(",\\s*"); // handles space after comma
                if (userData.length != 2) {
                    logger.warn("⚠️ Skipping malformed line: " + userLine);
                    continue;
                }
                String name = userData[0].trim();
                float balance = Float.parseFloat(userData[1].trim());
                UserRecord user = new UserRecord(name, balance);
                databaseConduit.save(user);
            } catch (Exception e) {
                logger.error("❌ Failed to parse userLine: " + userLine, e);
            }
        }
    }
}
