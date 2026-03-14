package career.lld.practice.splitwise.mi4;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class SettlementService {

    public void simplify(Map<String, Integer> balance) {

        Queue<Person> creditors = new LinkedList<>();
        Queue<Person> debtors = new LinkedList<>();

        for (var entry : balance.entrySet()) {

            int amt = entry.getValue();

            if (amt > 0)
                creditors.add(new Person(entry.getKey(), amt));
            else if (amt < 0)
                debtors.add(new Person(entry.getKey(), -amt));
        }

        while (!creditors.isEmpty() && !debtors.isEmpty()) {

            Person creditor = creditors.poll();
            Person debtor = debtors.poll();

            int settled = Math.min(creditor.amount, debtor.amount);

            System.out.println(debtor.name + " pays " + creditor.name + " " + settled);

            creditor.amount -= settled;
            debtor.amount -= settled;

            if (creditor.amount > 0)
                creditors.add(creditor);

            if (debtor.amount > 0)
                debtors.add(debtor);
        }
    }
}