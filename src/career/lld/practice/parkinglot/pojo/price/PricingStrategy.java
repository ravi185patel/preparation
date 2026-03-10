package career.lld.practice.parkinglot.pojo.price;

import career.lld.practice.parkinglot.pojo.Ticket;

public interface PricingStrategy {
    public double calculatePrice(Ticket ticket);
}
