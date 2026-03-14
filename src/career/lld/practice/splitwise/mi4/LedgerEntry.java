package career.lld.practice.splitwise.mi4;

import java.time.LocalDateTime;

class LedgerEntry {

    String id;
    String fromUser;
    String toUser;
    double amount;
    LocalDateTime createdAt;

    public LedgerEntry(String id, String fromUser, String toUser, double amount) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }
}