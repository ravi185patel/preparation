package career.lld.practice.bookmyshow.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


class BookingService {

    public Booking createBooking(User user, Show show, List<Integer> seatNumbers){
        List<Seat> seats = new ArrayList<>();
        for(Integer seatNo : seatNumbers){
            Seat seat = show.getSeat(seatNo);
            if(!seat.isAvailable()){
                throw new RuntimeException("Seat already booked");
            }
            seats.add(seat);
        }
        Booking booking = new Booking(UUID.randomUUID().toString(),user,show,seats);
        booking.confirmBooking();
        return booking;
    }

}