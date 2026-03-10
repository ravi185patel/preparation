package career.lld.practice.parkinglot.service;



import career.lld.practice.parkinglot.pojo.ParkingSpot;
import career.lld.practice.parkinglot.pojo.Ticket;
import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;

import java.util.UUID;

public abstract class ParkingProcessTemplate {

    public final Ticket processParking(Vehicle vehicle) {
        ParkingSpot spot = findSpot(vehicle);
        reserveSpot(spot, vehicle);
        Ticket ticket = generateTicket(vehicle, spot);
        processPayment(ticket);
        releaseSpot(spot);
        return  ticket;
    }

    abstract ParkingSpot findSpot(Vehicle vehicle);

    void reserveSpot(ParkingSpot spot, Vehicle vehicle) {
        spot.parkVehicle(vehicle);
    }

    Ticket generateTicket(Vehicle v, ParkingSpot s) {
        return new Ticket(UUID.randomUUID().toString(), v, s);
    }

    abstract void processPayment(Ticket ticket);

    void releaseSpot(ParkingSpot spot) {
        spot.unParkVehicle();
    }
}
