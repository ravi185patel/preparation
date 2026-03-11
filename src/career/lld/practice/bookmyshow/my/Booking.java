package career.lld.practice.bookmyshow.my;

import java.util.*;

class Booking {

    private String bookingId;
    private User user;
    private Show show;
    private List<Seat> seats;

    public Booking(String bookingId, User user, Show show, List<Seat> seats) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seats = seats;
    }

    public void confirmBooking() {

        for (Seat seat : seats) {
            seat.confirmBooking();
        }
    }


    /*public boolean confirmBooking() {

        while (true) {

            SeatState current = state.get();

            if (current.status != SeatStatus.LOCKED)
                return false;

            SeatState booked = new SeatState(SeatStatus.BOOKED, current.lockTime);

            if (state.compareAndSet(current, booked))
                return true;
        }
    }*/



    public List<Seat> getSeats() {
        return seats;
    }
}