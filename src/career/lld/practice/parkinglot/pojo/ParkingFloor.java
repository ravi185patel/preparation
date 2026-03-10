package career.lld.practice.parkinglot.pojo;

import career.lld.practice.parkinglot.constant.VehicleType;

import java.util.List;

public class ParkingFloor {
    private String id;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(String id, List<ParkingSpot> parkingSpots) {
        this.id = id;
        this.parkingSpots = parkingSpots;
    }

    public String getId() {
        return id;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    //    public ParkingSpot findFreeParkingSpot(VehicleType vehicleType){
//        for(ParkingSpot parkingSpot:parkingSpots){
//            if(parkingSpot.getVehicleType() == vehicleType && parkingSpot.isOccupied()==false){
//                return parkingSpot;
//            }
//        }
//        return null;
//    }
}
