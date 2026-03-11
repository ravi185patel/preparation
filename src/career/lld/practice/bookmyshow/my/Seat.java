package career.lld.practice.bookmyshow.my;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
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
/*
class Seat {

    private int seatNumber;
    private SeatStatus status = SeatStatus.AVAILABLE;

    private long lockTime;
    private static final long LOCK_TIMEOUT = 300000; // 5 minutes

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public synchronized boolean lockSeat() { // still it is acceptable

        if (status == SeatStatus.AVAILABLE || isLockExpired()) {

            status = SeatStatus.LOCKED;
            lockTime = System.currentTimeMillis();
            return true;
        }

        return false;
    }

    public synchronized void confirmBooking() {
        if (status == SeatStatus.LOCKED) {
            status = SeatStatus.BOOKED;
        }
    }

    public synchronized void releaseSeat() {
        status = SeatStatus.AVAILABLE;
    }

    private boolean isLockExpired() {

        return status == SeatStatus.LOCKED &&
                System.currentTimeMillis() - lockTime > LOCK_TIMEOUT;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }


    */
/*
    private AtomicBoolean occupied = new AtomicBoolean(false); it is work but in bookmyshow it is stage wise

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean tryBook() {
        return occupied.compareAndSet(false, true);
    }

    public boolean isAvailable() {
        return !occupied.get();
    }*//*


    // another way to do it.
    */
/*private AtomicReference<SeatState> state =
            new AtomicReference<>(new SeatState(SeatStatus.AVAILABLE, 0));

    private static final long LOCK_TIMEOUT = 300000; // 5 min

    public boolean tryLock() {

        while (true) {

            SeatState current = state.get();

            if (current.status == SeatStatus.BOOKED)
                return false;

            if (current.status == SeatStatus.AVAILABLE ||
                    isExpired(current)) {

                SeatState newState =
                        new SeatState(SeatStatus.LOCKED, System.currentTimeMillis());

                if (state.compareAndSet(current, newState))
                    return true;
            } else {
                return false;
            }
        }
    }

    private boolean isExpired(SeatState s) {
        return System.currentTimeMillis() - s.lockTime > LOCK_TIMEOUT;
    }
    public void release() {
        state.set(new SeatState(SeatStatus.AVAILABLE, 0));
    }
    *//*

}*/
