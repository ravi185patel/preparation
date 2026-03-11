package career.lld.practice.bookmyshow.mi1;

import java.util.*;

class Show {

    private String showId;

    private Movie movie;

    private Map<Integer, ShowSeat> seats = new HashMap<>();

    public Show(String showId, Movie movie, Screen screen) {

        this.showId = showId;
        this.movie = movie;

        for(Seat seat : screen.getSeats()){
            seats.put(seat.getSeatNumber(), new ShowSeat(seat));
        }
    }

    public ShowSeat getSeat(int seatNumber){
        return seats.get(seatNumber);
    }

}