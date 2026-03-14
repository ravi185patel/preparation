package career.lld.practice.splitwise.mi2;

import career.lld.practice.splitwise.common.Split;
import career.lld.practice.splitwise.common.User;

import java.util.HashMap;
import java.util.Map;

class SplitwiseService {

    Map<String, Map<String, Double>> balances = new HashMap<>();

    public void addExpense(Expense expense) {

        User paidBy = expense.getPaidBy();

        for (Split split : expense.getSplits()) {

            User user = split.getUser();

            if (user.getId().equals(paidBy.getId()))
                continue;

            double amount = split.getAmount();

            balances
                    .computeIfAbsent(user.getId(), k -> new HashMap<>())
                    .merge(paidBy.getId(), amount, Double::sum);
        }
    }

    public void printBalances() {
        System.out.println(balances);
    }
}