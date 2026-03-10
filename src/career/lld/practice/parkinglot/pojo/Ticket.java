package career.lld.practice.parkinglot.pojo;


import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amountDue;
    private ParkingSpot parkingSpot;

    public Ticket(String id, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = id;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    public Ticket(String id, Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime, double amountDue, ParkingSpot parkingSpot) {
        this.id = id;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.amountDue = amountDue;
        this.parkingSpot = parkingSpot;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", vehicle=" + vehicle +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", amountDue=" + amountDue +
                ", parkingSpot=" + parkingSpot +
                '}';
    }
}
