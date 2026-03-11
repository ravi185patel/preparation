package career.lld.practice.bookmyshow.mi1;

class CardPayment implements PaymentStrategy {

    public boolean pay(User user, double amount){

        System.out.println("Card payment successful");

        return true;
    }
}