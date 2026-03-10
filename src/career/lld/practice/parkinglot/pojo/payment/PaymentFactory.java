package career.lld.practice.parkinglot.pojo.payment;

import career.lld.practice.parkinglot.constant.PaymentType;

public class PaymentFactory {
    public PaymentStrategy paymentStrategy;

    public PaymentStrategy getPaymentStrategy(PaymentType paymentType){
        switch (paymentType){
            case CARD: return new CardPaymentStrategy();
            case UPI: return new UpiPaymentStrategy();
            default: return null;
        }

    }
}
