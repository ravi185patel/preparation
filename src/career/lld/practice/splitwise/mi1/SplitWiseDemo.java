package career.lld.practice.splitwise.mi1;

import career.lld.practice.splitwise.common.User;

import java.util.List;

public class SplitWiseDemo {
    public static void main(String[] args) {
        User A = new User("1", "A");
        User B = new User("2", "B");
        User C = new User("3", "C");

        Split s1 = new Split(A, 100);
        Split s2 = new Split(B, 100);
        Split s3 = new Split(C, 100);

        Expense expense = new Expense(300, A, List.of(s1, s2, s3));

        SplitwiseService service = new SplitwiseService();
        service.addExpense(expense);

        service.printBalances();
    }
}
