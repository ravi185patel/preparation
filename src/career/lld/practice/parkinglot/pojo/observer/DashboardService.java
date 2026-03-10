package career.lld.practice.parkinglot.pojo.observer;

import career.lld.practice.parkinglot.pojo.ParkingEvent;

public class DashboardService implements ParkingObserver{

    public int totalVehicles;

    @Override
    public void update(ParkingEvent event) {
        if(event.getEventType().equalsIgnoreCase("PARK")){
            totalVehicles++;
        }
        System.out.println("Total Vehicles:- "+totalVehicles);
    }
}
