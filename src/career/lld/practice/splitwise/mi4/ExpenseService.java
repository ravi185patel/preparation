package career.lld.practice.splitwise.mi4;

import career.lld.practice.splitwise.common.Split;
import career.lld.practice.splitwise.common.User;

class ExpenseService {

    private LedgerService ledgerService;

    public ExpenseService(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    public void addExpense(Expense expense) {

        User paidBy = expense.getPaidBy();

        for (Split split : expense.getSplits()) {

            User user = split.getUser();

            if (user.getId().equals(paidBy.getId()))
                continue;

            ledgerService.addEntry(
                    new LedgerEntry("1",
                            user.getName(),
                            paidBy.getName(),
                            split.getAmount()));
        }
    }
}