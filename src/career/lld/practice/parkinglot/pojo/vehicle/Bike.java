package career.lld.practice.parkinglot.pojo.vehicle;

import career.lld.practice.parkinglot.constant.VehicleType;

public class Bike extends Vehicle{
    public Bike(String licencePlatNo) {
        super(licencePlatNo, VehicleType.BIKE);
    }
}
