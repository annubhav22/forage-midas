package com.jpmc.midascore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MidasCoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MidasCoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        task_four_verifier(); // ✅ This runs automatically on startup
    }

    public void task_four_verifier() {
        // 🔴 Set a breakpoint on the next line to start debugging
        System.out.println("Inside task_four_verifier method...");

        // 🧠 Example debug variables
        int a = 10;
        int b = 5;
        int result = a + b;

        // You can watch 'a', 'b', 'result' in your debugger
        System.out.println("Result is: " + result);

        // Add your actual logic here
    }
}
