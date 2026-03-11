package career.lld.practice.bookmyshow.mi1;

import java.util.*;

class Screen {

    private String screenId;

    private List<Seat> seats = new ArrayList<>();

    public Screen(String screenId, int seatCount) {

        this.screenId = screenId;

        for(int i=1;i<=seatCount;i++){
            seats.add(new Seat(i,"A"));
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }
}