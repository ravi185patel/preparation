package career.lld.practice.parkinglot.pojo.payment;

import career.lld.practice.parkinglot.pojo.Ticket;

public interface PaymentStrategy {
    public void pay(Ticket ticket);
}
