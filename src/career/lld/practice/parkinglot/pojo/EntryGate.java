package career.lld.practice.parkinglot.pojo;

import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;
import career.lld.practice.parkinglot.service.ParkingSpotManager;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EntryGate {
    ParkingSpotManager parkingSpotManager;

    public EntryGate(ParkingSpotManager manager) {
        this.parkingSpotManager = manager;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        ParkingSpot parkingSpot =  parkingSpotManager.getParkingSpot(vehicle.getVehicleType());
        if(Objects.nonNull(parkingSpot)){
            parkingSpot.parkVehicle(vehicle);
            return new Ticket(UUID.randomUUID().toString(),vehicle, LocalDateTime.now(),null,0d,parkingSpot);
        }

//        for(ParkingFloor floor:parkingFloors){
//            ParkingSpot parkingSpot = floor.findFreeParkingSpot(vehicle.getVehicleType());
//            if(Objects.nonNull(parkingSpot)){
//                parkingSpot.parkVehicle(vehicle);
//                return new Ticket(UUID.randomUUID().toString(),vehicle,parkingSpot);
//            }
//        }
        return null;
    }
}
