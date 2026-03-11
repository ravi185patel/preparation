package career.lld.practice.bookmyshow.my;

class PaymentService {

    public boolean makePayment(User user, double amount) {

        System.out.println("Processing payment of " + amount);

        return true; // assume payment success
    }
}