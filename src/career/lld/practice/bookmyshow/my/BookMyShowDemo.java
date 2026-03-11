package career.lld.practice.bookmyshow.my;

import java.util.*;

public class BookMyShowDemo {

    public static void main(String[] args) {

        Movie movie = new Movie("M1", "Avengers", 180);

        Screen screen = new Screen("S1", 10);

        Show show = new Show("SHOW1", movie, screen, new Date());

        User user = new User("U1", "Aryan");

        BookingService bookingService = new BookingService();

        List<Integer> seats = Arrays.asList(1, 2, 3);

        Booking booking = bookingService.bookSeats(user, show, seats);

        System.out.println("Booking successful for seats: " + seats);
    }
}