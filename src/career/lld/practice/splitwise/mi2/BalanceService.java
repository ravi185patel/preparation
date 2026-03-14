package career.lld.practice.splitwise.mi2;

import java.util.HashMap;
import java.util.Map;

class BalanceService {

    private Map<String, Map<String, Double>> balances = new HashMap<>();

    public void updateBalance(String from, String to, double amount) {

        balances
            .computeIfAbsent(from, k -> new HashMap<>())
            .merge(to, amount, Double::sum);
    }

    public void showBalances() {
        System.out.println(balances);
    }
}