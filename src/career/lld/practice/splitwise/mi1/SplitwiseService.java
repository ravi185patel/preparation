package career.lld.practice.splitwise.mi1;

import career.lld.practice.splitwise.common.User;

import java.util.*;

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