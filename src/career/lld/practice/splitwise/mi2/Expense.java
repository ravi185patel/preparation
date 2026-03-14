package career.lld.practice.splitwise.mi2;

import career.lld.practice.splitwise.common.Split;
import career.lld.practice.splitwise.common.User;

import java.util.List;

class Expense {

    private double amount;
    private User paidBy;
    private List<Split> splits;

    public Expense(double amount, User paidBy, List<Split> splits) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }
}