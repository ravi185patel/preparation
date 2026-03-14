package career.lld.practice.splitwise.mi4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LedgerService {

    List<LedgerEntry> ledger = new ArrayList<>();

    public void addEntry(LedgerEntry entry) {
        ledger.add(entry);
    }

    public Map<String, Integer> calculateNetBalance() {

        Map<String, Integer> balance = new HashMap<>();

        for (LedgerEntry entry : ledger) {

            balance.put(entry.fromUser,
                    balance.getOrDefault(entry.fromUser, 0) - (int) entry.amount);

            balance.put(entry.toUser,
                    balance.getOrDefault(entry.toUser, 0) + (int) entry.amount);
        }

        return balance;
    }
}