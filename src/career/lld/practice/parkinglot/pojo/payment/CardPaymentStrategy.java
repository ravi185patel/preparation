package career.lld.practice.parkinglot.pojo.payment;

import career.lld.practice.parkinglot.pojo.Ticket;

public class CardPaymentStrategy implements PaymentStrategy{

    @Override
    public void pay(Ticket ticket) {
        ticket.setAmountDue(ticket.getAmountDue()+10.0);
    }
}
