package career.designpattern.gofdesignpattern.behaviorls.observer;

import java.math.BigDecimal;
import java.util.ArrayList;

/*

✔ Intent
Notify multiple observers when something changes.

✔ Real-life analogy
YouTube channel → subscribers
Stock price update → listeners
Event-driven systems

✔ Spring Boot usage
Spring Event System (ApplicationEventPublisher)
Kafka consumers (event-driven microservices)
WebSocket broadcasting

 */

interface Observer {
    public void update(Observer observer,String productName, BigDecimal bidAmount);
}

interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
    public void setBidAmount(Observer observer,BigDecimal newBidAmount);
}
class Product implements Subject{
    private ArrayList<Observer> observers = new ArrayList<>();
    private String productName;
    private BigDecimal bidAmount;
    private Observer observer;
    public Product(String productName, BigDecimal bidAmount){
        this.productName=productName;
        this.bidAmount=bidAmount;
    }
    @Override
    public void setBidAmount(Observer observer,BigDecimal newBidAmount){
        System.out.println("-----------------New bid placed----------------");
        int res=bidAmount.compareTo(newBidAmount);
        if(res==-1){
            this.observer=observer;
            this.bidAmount=newBidAmount;
//            setChanged();
            notifyObservers();
        }
        else {
            System.out.println("New bid amount cannot be less or equal to current bid amount: "+this.bidAmount);
        }
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("-----------------"+observer+" has withdrawn from bidding----------------");
    }
    @Override
    public void notifyObservers() {
        System.out.println("-----------------New bid placed----------------");
        for (Observer ob : observers) {
            ob.update(this.observer,this.productName,this.bidAmount );
        }
    }

    public Observer getObserver() {
        return observer;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public String getProductName() {
        return productName;
    }
}

class Bidder implements Observer{
    Product observable;
    String bidderName;
    public Bidder(String bidderName) {
        this.bidderName = bidderName;
    }
    @Override
    public void update(Observer observer,String productName, BigDecimal bidAmount){
//        this.observable = (Product) observable;
//        String name = this.observable.getObserver().toString();
//        if(name.equals(bidderName))
//        {
//            System.out.println("Hello "+bidderName+"! New bid of amount "+this.observable.getBidAmount()+" has been placed on "+this.observable.getProductName()+" by you");
//        }
//        if (!name.equals(bidderName))
//            System.out.println("Hello "+bidderName+"! New bid of amount "+this.observable.getBidAmount()+" has been placed on "+this.observable.getProductName()+" by "+this.observable.getObserver());

        if(observer.toString().equals(bidderName)){
            System.out.println("Hello "+bidderName+"! New bid of amount "+bidAmount+" has been placed on "+productName+" by you");
        }
        if(!observer.toString().equals(bidderName)) {
            System.out.println("Hello " + bidderName + "! New bid of amount " + bidAmount + " has been placed on " + productName + " by " + observer);
        }
    }
    @Override
    public String toString(){
        return bidderName;
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        Subject product=new Product("36 inch LED TV",new BigDecimal(350));
        Observer bidder1=new Bidder("Alex Parker");
        Observer bidder2=new Bidder("Henry Smith");
        Observer bidder3=new Bidder("Mary Peterson");
        product.registerObserver(bidder1);
        product.registerObserver(bidder2);
        product.registerObserver(bidder3);
        product.setBidAmount(bidder1, new BigDecimal(375));
        product.removeObserver(bidder2);
        product.setBidAmount(bidder3, new BigDecimal(400));
    }
}
