package career.lld.practice.parkinglot.pojo.observer;

import career.lld.practice.parkinglot.pojo.ParkingEvent;

public interface ParkingObserver {
    void update(ParkingEvent event);
}
