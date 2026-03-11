package career.lld.practice.bookmyshow.mi1;

import java.util.*;

class BookingService {

    private PaymentStrategy payment;

    private NotificationService notification;

    public BookingService(
            PaymentStrategy payment,
            NotificationService notification
    ){
        this.payment = payment;
        this.notification = notification;
    }

    public Booking createBooking(User user, Show show, List<Integer> seatNumbers){

        List<ShowSeat> lockedSeats = new ArrayList<>();

        for(int seatNo : seatNumbers){

            ShowSeat seat = show.getSeat(seatNo);

            if(!seat.tryLock()){

                for(ShowSeat s : lockedSeats){
                    s.release();
                }

                throw new RuntimeException("Seat unavailable");
            }

            lockedSeats.add(seat);
        }

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                user,
                show,
                lockedSeats
        );

        boolean paymentSuccess = payment.pay(user, seatNumbers.size()*200);

        if(paymentSuccess){

            for(ShowSeat s : lockedSeats){
                s.confirmBooking();
            }

            booking.confirm();

            notification.sendConfirmation(booking);

        } else {

            for(ShowSeat s : lockedSeats){
                s.release();
            }

            booking.fail();
        }

        return booking;
    }
}