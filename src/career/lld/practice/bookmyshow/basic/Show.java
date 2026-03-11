package career.lld.practice.bookmyshow.basic;


import java.util.ArrayList;
import java.util.List;

class Show {

    private String showId;
    private Movie movie;
    private List<Seat> seats;

    public Show(String showId, Movie movie, int totalSeats) {
        this.showId = showId;
        this.movie = movie;
        seats = new ArrayList<>();

        for(int i=1;i<=totalSeats;i++){
            seats.add(new Seat(i));
        }
    }

    public List<Seat> getAvailableSeats() {

        List<Seat> available = new ArrayList<>();

        for(Seat s : seats){
            if(s.isAvailable()){
                available.add(s);
            }
        }

        return available;
    }

    public Seat getSeat(int seatNo){
        return seats.get(seatNo-1);
    }

}