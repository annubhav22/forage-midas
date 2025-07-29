package com.jpmc.midascore.foundation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class Ledger {

    private final Map<String, Balance> balances = new ConcurrentHashMap<>();

    public void updateBalance(String username, float amount) {
        balances.computeIfAbsent(username, k -> new Balance(0));
        Balance balance = balances.get(username);
        balance.setAmount(balance.getAmount() + amount);
    }

    public int getBalance(String username) {
        return (int) balances.getOrDefault(username, new Balance(0)).getAmount();
    }

    public void reset() {
        balances.clear();
    }
}
