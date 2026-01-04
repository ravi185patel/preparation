package career.java.scenario;


/*
There are three distinct responsibilities:
payment method logic, payment gateway logic, and orchestration logic.
We use an abstract class for payment methods,
 an interface for gateways,
 and a service/factory layer to integrate them.
 This separation keeps the system extensible and loosely coupled.

 1️⃣ Payment Method Logic
👉 How the user pays
Examples:
Card
UPI
NetBanking

Responsibility
Decide payment flow
Validate method-specific data
Trigger gateway call

2️⃣ Payment Gateway Logic

👉 Who processes the payment
Examples:
RazorPay
Stripe
PayPal

Responsibility
Talk to external systems
Process transaction
Return success/failure

3️⃣ Orchestration / Integration Logic

👉 Who connects payment method with gateway
Examples:
PaymentService
Factory
Controller layer

Responsibility
Choose payment method
Choose gateway
Wire them together
 */

interface PaymentGateWay{
    public void pay();
}
abstract class Payment {

    protected  PaymentGateWay paymentGateWay;

    public Payment(PaymentGateWay paymentGateWay) {
        this.paymentGateWay = paymentGateWay;
    }

    abstract public void pay();
    public void receipt(){
        System.out.println("Payment done");
    }
}
class Card extends Payment{

    public Card(PaymentGateWay paymentGateWay) {
        super(paymentGateWay);
    }

    @Override
    public void pay() {
        System.out.println("Payment through Card...");
        paymentGateWay.pay();
    }
}
class UPI extends Payment{
    public UPI(PaymentGateWay paymentGateWay) {
        super(paymentGateWay);
    }

    @Override
    public void pay() {
        System.out.println("Payment through UPI...");
        paymentGateWay.pay();
    }
}
class Netbanking extends Payment{
    public Netbanking(PaymentGateWay paymentGateWay) {
        super(paymentGateWay);
    }

    @Override
    public void pay() {
        System.out.println("Payment through Netbanking...");
        paymentGateWay.pay();
    }
}

class RazorPay implements PaymentGateWay{
    @Override
    public void pay() {
        System.out.println("PaymentGateWay is RazorPay...");
    }
}

public class PaymentGatewayIntegration {

    public static void main(String[] args) {
        PaymentGateWay paymentGateWay = new RazorPay();
        Payment p = new UPI(paymentGateWay);
        p.pay();
        p.receipt();


    }
}
