package career.lld.practice.bookmyshow.my;

import java.util.concurrent.atomic.AtomicReference;

class ShowSeat {

    private Seat seat;

    private AtomicReference<SeatStatus> status =
            new AtomicReference<>(SeatStatus.AVAILABLE);

    private long lockTime;

    private static final long LOCK_TIMEOUT = 300000;

    public ShowSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean tryLock() {

        if(status.compareAndSet(SeatStatus.AVAILABLE, SeatStatus.LOCKED)) {
            lockTime = System.currentTimeMillis();
            return true;
        }

        return false;
    }

    public boolean confirmBooking() {
        return status.compareAndSet(SeatStatus.LOCKED, SeatStatus.BOOKED);
    }

    public void release() {
        status.set(SeatStatus.AVAILABLE);
    }

}