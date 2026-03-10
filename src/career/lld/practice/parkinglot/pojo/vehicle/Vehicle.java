package career.lld.practice.parkinglot.pojo.vehicle;

import career.lld.practice.parkinglot.constant.VehicleType;

public abstract class Vehicle {
    private String licencePlatNo;
    private VehicleType vehicleType;

    public Vehicle(String licencePlatNo, VehicleType vehicleType) {
        this.licencePlatNo = licencePlatNo;
        this.vehicleType = vehicleType;
    }

    public String getLicencePlatNo() {
        return licencePlatNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licencePlatNo='" + licencePlatNo + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
