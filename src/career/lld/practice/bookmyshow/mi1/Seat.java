package career.lld.practice.bookmyshow.mi1;

class Seat {

    private int seatNumber;
    private String row;

    public Seat(int seatNumber, String row) {
        this.seatNumber = seatNumber;
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}