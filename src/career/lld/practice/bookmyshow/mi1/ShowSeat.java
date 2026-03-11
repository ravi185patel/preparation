package career.lld.practice.bookmyshow.mi1;

import java.util.concurrent.atomic.AtomicReference;

class ShowSeat {

    private Seat seat;

    private AtomicReference<SeatStatus> status =
            new AtomicReference<>(SeatStatus.AVAILABLE);

    public ShowSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean tryLock(){

        return status.compareAndSet(
                SeatStatus.AVAILABLE,
                SeatStatus.LOCKED
        );
    }

    public boolean confirmBooking(){

        return status.compareAndSet(
                SeatStatus.LOCKED,
                SeatStatus.BOOKED
        );
    }

    public void release(){

        status.set(SeatStatus.AVAILABLE);
    }

    public Seat getSeat(){
        return seat;
    }
}