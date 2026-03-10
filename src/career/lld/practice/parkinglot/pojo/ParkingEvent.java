package career.lld.practice.parkinglot.pojo;

import java.time.LocalDateTime;

public class ParkingEvent {
    private String vehicleId;
    LocalDateTime time;
    private String eventType;

    public ParkingEvent(String vehicleId, LocalDateTime time, String eventType) {
        this.vehicleId = vehicleId;
        this.time = time;
        this.eventType = eventType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getEventType() {
        return eventType;
    }
}
