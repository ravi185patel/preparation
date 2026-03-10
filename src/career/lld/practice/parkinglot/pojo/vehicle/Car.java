package career.lld.practice.parkinglot.pojo.vehicle;

import career.lld.practice.parkinglot.constant.VehicleType;

public class Car extends Vehicle{
    public Car(String licencePlatNo) {
        super(licencePlatNo, VehicleType.CAR);
    }
}
