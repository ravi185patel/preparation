package target2023.LLD.LowLevelDesign.DesignSplitwise.Expense;

import target2023.LLD.LowLevelDesign.DesignSplitwise.Expense.Split.EqualExpenseSplit;
import target2023.LLD.LowLevelDesign.DesignSplitwise.Expense.Split.ExpenseSplit;
import target2023.LLD.LowLevelDesign.DesignSplitwise.Expense.Split.PercentageExpenseSplit;
import target2023.LLD.LowLevelDesign.DesignSplitwise.Expense.Split.UnequalExpenseSplit;

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
