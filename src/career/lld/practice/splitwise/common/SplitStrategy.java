package career.lld.practice.splitwise.common;

import java.util.List;

public interface SplitStrategy {

    void calculate(double amount, List<Split> splits);
}