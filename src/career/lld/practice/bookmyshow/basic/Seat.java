package career.lld.practice.bookmyshow.basic;

class Seat {
    private int seatNumber;
    private SeatStatus status;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.status = SeatStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    public void bookSeat() {
        status = SeatStatus.BOOKED;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
