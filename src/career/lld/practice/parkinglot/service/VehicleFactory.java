package career.lld.practice.parkinglot.service;

import career.lld.practice.parkinglot.constant.VehicleType;
import career.lld.practice.parkinglot.pojo.vehicle.Bike;
import career.lld.practice.parkinglot.pojo.vehicle.Car;
import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;

public class VehicleFactory {
    public Vehicle vehicle;

    public Vehicle getVehicle(VehicleType vehicleType,String number){
        switch (vehicleType){
            case CAR: return new Car(number);
            case BIKE: return new Bike(number);
            default: return null;
        }
    }
}
