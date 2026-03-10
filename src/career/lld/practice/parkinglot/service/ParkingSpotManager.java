package career.lld.practice.parkinglot.service;

import career.lld.practice.parkinglot.constant.VehicleType;
import career.lld.practice.parkinglot.pojo.ParkingSpot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ParkingSpotManager {
    private Map<VehicleType, Queue<ParkingSpot>> availableSpots;

    public ParkingSpotManager() {
        this.availableSpots = new HashMap<>();
    }

    public ParkingSpot getParkingSpot(VehicleType vehicleType){
        Queue<ParkingSpot> queue = availableSpots.get(vehicleType);
        if(queue == null || queue.size()==0){
            return null;
        }
        return queue.poll();
    }

    public void releaseParkingSpot(ParkingSpot parkingSpot){
        availableSpots.get(parkingSpot.getVehicleType()).offer(parkingSpot);
    }

    public void addParkingSpot(ParkingSpot parkingSpot){
        availableSpots.putIfAbsent(parkingSpot.getVehicleType(),new LinkedList<>());
        availableSpots.get(parkingSpot.getVehicleType()).offer(parkingSpot);
    }
}
