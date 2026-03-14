package career.lld.practice.splitwise.common;

import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    public void calculate(double amount, List<Split> splits) {

        double splitAmount = amount / splits.size();

        for (Split split : splits) {
            split.setAmount(splitAmount);
        }
    }
}