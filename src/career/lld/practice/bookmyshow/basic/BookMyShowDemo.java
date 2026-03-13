package career.lld.practice.bookmyshow.basic;




import java.util.*;

public class BookMyShowDemo {

    public static void main(String[] args) {

        Movie movie = new Movie("1","Avengers",180);
        Show show = new Show("S1",movie,10);
        User user = new User("U1","Aryan");
        BookingService bookingService = new BookingService();
        List<Integer> seats = Arrays.asList(1,2,3);
        Booking booking = bookingService.createBooking(user,show,seats);
        System.out.println("Booking successful");

    }

}