package com.jpmc.midascore;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileLoader {

    private static final Logger logger = LoggerFactory.getLogger(FileLoader.class);

    public String[] loadStrings(String path) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(path)) {
            if (inputStream == null) {
                logger.error("❌ Could not load file from path: {}", path);
                return null;
            }

            String fileText = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            return fileText.split("\\R"); // \R = any line break (platform-independent)
        } catch (Exception e) {
            logger.error("❌ Exception while loading file: {}", path, e);
            return null;
        }
    }
}
