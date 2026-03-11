package career.lld.practice.bookmyshow.mi1;

import java.util.*;

public class Demo {

    public static void main(String[] args) {

        Movie movie = new Movie("M1","Avengers",180);

        Screen screen = new Screen("S1",10);

        Show show = new Show("SHOW1",movie,screen);

        User user = new User("U1","Aryan");

        BookingService bookingService = new BookingService(
                new CardPayment(),
                new EmailNotificationService()
        );

        Booking booking = bookingService.createBooking(
                user,
                show,
                Arrays.asList(1,2,3)
        );

        System.out.println("Booking completed");
    }
}