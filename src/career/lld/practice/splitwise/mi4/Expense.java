package career.lld.practice.splitwise.mi4;

import career.lld.practice.splitwise.common.Split;
import career.lld.practice.splitwise.common.User;

import java.util.List;

class Expense {

    private String id;
    private double amount;
    private User paidBy;
    private List<Split> splits;

    public Expense(String id, double amount, User paidBy, List<Split> splits) {
        this.id = id;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }
}