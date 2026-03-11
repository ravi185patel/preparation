package career.lld.practice.bookmyshow.mi1;

import java.util.*;

class Booking {

    private String bookingId;

    private User user;

    private Show show;

    private List<ShowSeat> seats;

    private BookingStatus status;

    public Booking(String bookingId, User user, Show show, List<ShowSeat> seats) {

        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seats = seats;

        this.status = BookingStatus.PENDING;
    }

    public void confirm(){
        status = BookingStatus.CONFIRMED;
    }

    public void fail(){
        status = BookingStatus.FAILED;
    }

}