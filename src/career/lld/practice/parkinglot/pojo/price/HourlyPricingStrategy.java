package career.lld.practice.parkinglot.pojo.price;

import career.lld.practice.parkinglot.pojo.Ticket;

import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy{

    @Override
    public double calculatePrice(Ticket ticket) { // correct logic
        Duration duration = Duration.between(ticket.getEntryTime(),ticket.getExitTime());
        long hours = duration.toHours();
        return hours > 0 ? hours*20:20;
    }
}
