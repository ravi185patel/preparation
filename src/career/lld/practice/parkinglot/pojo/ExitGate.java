package career.lld.practice.parkinglot.pojo;

import career.lld.practice.parkinglot.pojo.price.PricingStrategy;
import career.lld.practice.parkinglot.service.ParkingSpotManager;

import java.time.LocalDateTime;

public class ExitGate {
    ParkingSpotManager parkingSpotManager;
    PricingStrategy pricingStrategy;

    public ExitGate(ParkingSpotManager parkingSpotManager, PricingStrategy pricingStrategy) {
        this.parkingSpotManager = parkingSpotManager;
        this.pricingStrategy = pricingStrategy;
    }

    public double exitVehicle(Ticket ticket){
        ticket.setExitTime(LocalDateTime.now());
        double amount = pricingStrategy.calculatePrice(ticket);
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.unParkVehicle();
        parkingSpotManager.releaseParkingSpot(parkingSpot);
        ticket.setAmountDue(amount);
        return amount;
    }
}
