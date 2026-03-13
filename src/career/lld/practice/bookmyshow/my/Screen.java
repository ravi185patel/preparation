package career.lld.practice.bookmyshow.my;

import java.util.*;

class Screen {

    private String screenId;
    private List<Seat> seats = new ArrayList<>();

    public Screen(String screenId, int totalSeats) {
        this.screenId = screenId;

        for (int i = 1; i <= totalSeats; i++) {
//            seats.add(new Seat(i));
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }
}