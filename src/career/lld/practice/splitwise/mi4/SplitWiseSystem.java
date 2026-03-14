package career.lld.practice.splitwise.mi4;

import career.lld.practice.splitwise.common.EqualSplitStrategy;
import career.lld.practice.splitwise.common.Split;
import career.lld.practice.splitwise.common.SplitStrategy;
import career.lld.practice.splitwise.common.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitWiseSystem {

    public static void main(String[] args) {

        User A = new User("1", "A");
        User B = new User("2", "B");
        User C = new User("3", "C");

        List<Split> splits = new ArrayList<>();

        splits.add(new Split(A));
        splits.add(new Split(B));
        splits.add(new Split(C));

        SplitStrategy strategy = new EqualSplitStrategy();
        strategy.calculate(300, splits);

        Expense expense = new Expense("E1", 300, A, splits);

        LedgerService ledgerService = new LedgerService();
        ExpenseService expenseService = new ExpenseService(ledgerService);

        expenseService.addExpense(expense);

        Map<String, Integer> balances = ledgerService.calculateNetBalance();

        System.out.println("Net Balance: " + balances);

        SettlementService settlementService = new SettlementService();
        settlementService.simplify(balances);
    }
}