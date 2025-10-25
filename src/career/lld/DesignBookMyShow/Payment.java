package career.lld.DesignBookMyShow;

import java.util.UUID;

public class Payment {

    UUID id;
    UUID ticketId;
    //Other payment details


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }
}
