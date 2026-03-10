package career.lld.practice.parkinglot.pojo;

import career.lld.practice.parkinglot.constant.VehicleType;
import career.lld.practice.parkinglot.pojo.vehicle.Vehicle;

public class ParkingSpot {
    private String id;
    private String floorId;
    private VehicleType vehicleType;
    private Vehicle vehicle;
    private boolean occupied;

    public ParkingSpot(String id, String floorId, VehicleType vehicleType) {
        this.id = id;
        this.floorId = floorId;
        this.vehicleType = vehicleType;
        this.vehicle = null;
        this.occupied = false;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public void parkVehicle(Vehicle vehicle){
        this.vehicle= vehicle;
        this.occupied = true;
    }

    public void unParkVehicle(){
        this.vehicle= null;
        this.occupied=false;
    }

    public String getId() {
        return id;
    }

    public String getFloorId() {
        return floorId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id='" + id + '\'' +
                ", floorId='" + floorId + '\'' +
                ", vehicleType=" + vehicleType +
                ", vehicle=" + vehicle +
                ", occupied=" + occupied +
                '}';
    }
}
