package career.lld.practice.bookmyshow.mi1;

class EmailNotificationService implements NotificationService {

    public void sendConfirmation(Booking booking){

        System.out.println("Email sent for booking");
    }
}