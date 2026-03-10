package career.lld.practice.parkinglot.pojo.spot;

import career.lld.practice.parkinglot.constant.VehicleType;
import career.lld.practice.parkinglot.pojo.ParkingSpot;
import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;

public class NearestSpotStrategy implements SpotAllocationStrategy {

    public ParkingSpot allocateSpot(Vehicle vehicle) {
        System.out.println("Finding nearest spot");
        return new ParkingSpot("1","1", VehicleType.CAR);
    }
}