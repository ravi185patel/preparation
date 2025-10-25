package career.lld.LLDElevatorDesign;

import java.util.UUID;

public class Floor {

    UUID id;
    int floorNumber;
    ExternalDispatcher externalDispatcher;


    public Floor(int floorNumber){
        this.floorNumber = floorNumber;
        externalDispatcher = new ExternalDispatcher();
    }
    public void pressButton(Direction direction) {

        externalDispatcher.submitExternalRequest(floorNumber, direction);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public ExternalDispatcher getExternalDispatcher() {
        return externalDispatcher;
    }

    public void setExternalDispatcher(ExternalDispatcher externalDispatcher) {
        this.externalDispatcher = externalDispatcher;
    }
}
