package career.lld.practice.bookmyshow.basic;

import java.util.List;

class Booking {

    private String bookingId;
    private User user;
    private Show show;
    private List<Seat> seats;

    public Booking(String bookingId,User user,Show show,List<Seat> seats){
        this.bookingId=bookingId;
        this.user=user;
        this.show=show;
        this.seats=seats;
    }

    public void confirmBooking(){
        for(Seat s: seats){
            s.bookSeat();
        }
    }

}