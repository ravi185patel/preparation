package career.lld.DesignSplitwise.Expense;

import career.lld.DesignSplitwise.Expense.Split.EqualExpenseSplit;
import career.lld.DesignSplitwise.Expense.Split.ExpenseSplit;
import career.lld.DesignSplitwise.Expense.Split.PercentageExpenseSplit;
import career.lld.DesignSplitwise.Expense.Split.UnequalExpenseSplit;

public class SplitFactory {

    public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {

        switch (splitType) {
            case EQUAL:
                return new EqualExpenseSplit();
            case UNEQUAL:
                return new UnequalExpenseSplit();
            case PERCENTAGE:
                return new PercentageExpenseSplit();
            default:
                return null;
        }
    }
}
