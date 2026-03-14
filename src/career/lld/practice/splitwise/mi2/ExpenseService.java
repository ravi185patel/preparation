package career.lld.practice.splitwise.mi2;

import career.lld.practice.splitwise.common.Split;
import career.lld.practice.splitwise.common.User;

class ExpenseService {

    private BalanceService balanceService;

    public ExpenseService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public void addExpense(Expense expense) {

        User paidBy = expense.getPaidBy();

        for (Split split : expense.getSplits()) {

            User user = split.getUser();

            if (user.getId().equals(paidBy.getId()))
                continue;

            balanceService.updateBalance(
                    user.getId(),
                    paidBy.getId(),
                    split.getAmount());
        }
    }
}