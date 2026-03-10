package career.lld.practice.parkinglot.service;

import career.lld.practice.parkinglot.pojo.ParkingSpot;
import career.lld.practice.parkinglot.pojo.Ticket;
import career.lld.practice.parkinglot.pojo.payment.PaymentStrategy;
import career.lld.practice.parkinglot.pojo.spot.SpotAllocationStrategy;
import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;

public class ParkingProcess extends ParkingProcessTemplate{

    private SpotAllocationStrategy spotAllocationStrategy;
    private PaymentStrategy paymentStrategy;

    public ParkingProcess(SpotAllocationStrategy spotAllocationStrategy, PaymentStrategy paymentStrategy) {
        this.spotAllocationStrategy = spotAllocationStrategy;
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    ParkingSpot findSpot(Vehicle vehicle) {
        return spotAllocationStrategy.allocateSpot(vehicle);
    }

    @Override
    void processPayment(Ticket ticket) {
        paymentStrategy.pay(ticket);
    }
}
