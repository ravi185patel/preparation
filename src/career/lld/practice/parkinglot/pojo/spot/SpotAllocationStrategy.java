package career.lld.practice.parkinglot.pojo.spot;

import career.lld.practice.parkinglot.pojo.ParkingSpot;
import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;

public interface SpotAllocationStrategy {
    ParkingSpot allocateSpot(Vehicle vehicle);
}