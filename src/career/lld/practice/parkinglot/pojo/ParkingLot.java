package career.lld.practice.parkinglot.pojo;

import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;
import career.lld.practice.parkinglot.service.ParkingSpotManager;

import java.util.List;

public class ParkingLot {

    public static ParkingLot parkingLot;
    private String id;
    private List<ParkingFloor> parkingFloors;
    private ParkingSpotManager parkingSpotManager;

    private EntryGate entryGate;
    private ExitGate exitGate;

    public ParkingLot(String id, List<ParkingFloor> parkingFloors) {
        this.id = id;
        this.parkingFloors = parkingFloors;
    }

    private ParkingLot(String id, List<ParkingFloor> parkingFloors, ParkingSpotManager parkingSpotManager) {
        this.id = id;
        this.parkingFloors = parkingFloors;
        this.parkingSpotManager = parkingSpotManager;
    }

    public ParkingLot(String id, List<ParkingFloor> parkingFloors,EntryGate entryGate, ExitGate exitGate) {
        this.id = id;
        this.parkingFloors = parkingFloors;
        this.parkingSpotManager = parkingSpotManager;
        this.entryGate = entryGate;
        this.exitGate = exitGate;
    }

    public static ParkingLot getParkingLot(String id, List<ParkingFloor> parkingFloors){
        if(parkingLot == null) {
            synchronized (ParkingLot.class) {
                if(parkingLot == null){
                    parkingLot = new ParkingLot(id,parkingFloors);
                }
            }
        }
        return parkingLot;
    }

    public static ParkingLot getParkingLot(String id, List<ParkingFloor> parkingFloors, ParkingSpotManager parkingSpotManager){
        if(parkingLot == null) {
            synchronized (ParkingLot.class) {
                if(parkingLot == null){
                    parkingLot = new ParkingLot(id,parkingFloors,parkingSpotManager);
                }
            }
        }
        return parkingLot;
    }

    public static ParkingLot getParkingLot(String id, List<ParkingFloor> parkingFloors, EntryGate entryGate,ExitGate exitGate){
        if(parkingLot == null) {
            synchronized (ParkingLot.class) {
                if(parkingLot == null){
                    parkingLot = new ParkingLot(id,parkingFloors,entryGate,exitGate);
                }
            }
        }
        return parkingLot;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        Ticket ticket = entryGate.parkVehicle(vehicle);
        return ticket;
       /* ParkingSpot parkingSpot =  parkingSpotManager.getParkingSpot(vehicle.getVehicleType());
        if(Objects.nonNull(parkingSpot)){
            parkingSpot.parkVehicle(vehicle);
            return new Ticket(UUID.randomUUID().toString(),vehicle,parkingSpot);
        }*/

//        for(ParkingFloor floor:parkingFloors){
//            ParkingSpot parkingSpot = floor.findFreeParkingSpot(vehicle.getVehicleType());
//            if(Objects.nonNull(parkingSpot)){
//                parkingSpot.parkVehicle(vehicle);
//                return new Ticket(UUID.randomUUID().toString(),vehicle,parkingSpot);
//            }
//        }
//        return null;
    }

    public double unParkVehicle(Ticket ticket){
        return exitGate.exitVehicle(ticket);
    }

    public EntryGate getEntryGate() {
        return entryGate;
    }

    public ExitGate getExitGate() {
        return exitGate;
    }

    public String getId() {
        return id;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }
}
