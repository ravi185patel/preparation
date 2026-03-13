package career.lld.practice.bookmyshow.my;

import java.util.*;

class BookingService {

    private PaymentService paymentService = new PaymentService();


    public Booking bookSeats(User user, Show show, List<Integer> seatNumbers) {

        List<Seat> lockedSeats = new ArrayList<>();

        for (int seatNo : seatNumbers) {

//            Seat seat = show.getSeat(seatNo);
//
//            if (!seat.lockSeat()) {
//
//                for (Seat s : lockedSeats) {
//                    s.releaseSeat();
//                }
//
//                throw new RuntimeException("Seat not available");
//            }
//
//            lockedSeats.add(seat);
        }

        boolean paymentSuccess = paymentService.makePayment(user, seatNumbers.size() * 200);

        if (!paymentSuccess) {

            for (Seat s : lockedSeats) {
//                s.releaseSeat();
            }

            throw new RuntimeException("Payment failed");
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), user, show, lockedSeats);

        booking.confirmBooking();

        return booking;
    }
}