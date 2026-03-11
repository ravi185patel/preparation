package career.lld.practice.bookmyshow.my;

import java.util.*;

class Show {

    private String showId;

    private Map<Integer, ShowSeat> seats = new HashMap<>();

    public Show(List<Seat> screenSeats) {

        for(Seat s : screenSeats) {
            seats.put(s.getSeatNumber(), new ShowSeat(s));
        }
    }

    public ShowSeat getSeat(int seatNumber) {
        return seats.get(seatNumber);
    }
}

//class Show {
//
//    private String showId;
//    private Movie movie;
//    private Screen screen;
//    private Date startTime;
//
//    public Show(String showId, Movie movie, Screen screen, Date startTime) {
//        this.showId = showId;
//        this.movie = movie;
//        this.screen = screen;
//        this.startTime = startTime;
//    }
//
//    public List<Seat> getSeats() {
//        return screen.getSeats();
//    }
//
//    public Seat getSeat(int seatNumber) {
//        return screen.getSeats().get(seatNumber - 1);
//    }
//
//    public Movie getMovie() {
//        return movie;
//    }
//}