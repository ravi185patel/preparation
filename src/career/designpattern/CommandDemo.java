package career.designpattern;

import java.util.*;
interface Order{
    public void execute();
}
class Stock{
    private String name="ABC";
    private int quantity=10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
     }
     public void sell(){
        System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] sold");
     }
}
class BuyStock implements Order{
    private Stock stock;

    public BuyStock(Stock stock){
        this.stock=stock;
    }

    @Override
    public void execute() {
      this.stock.buy();
    }
    
}
class SellStock implements Order{
    private Stock stock;

    public SellStock(Stock stock){
        this.stock=stock;
    }

    @Override
    public void execute() {
      this.stock.sell();
    }
    
}
class Broker{
    private List<Order> orderLst=new ArrayList<>();
    public void takeOrder(Order order){
        orderLst.add(order);
    }
    public void placeOrders(){
        for(Order order:orderLst){
            order.execute();
        }
        orderLst.clear();
    }
}
public class CommandDemo {
    public static void main(String[] args) {
        Stock stock=new Stock();
        BuyStock buyStock=new BuyStock(stock);
        SellStock sellStock=new SellStock(stock);
        Broker broker=new Broker();
        broker.takeOrder(buyStock);
        broker.takeOrder(sellStock);
        broker.placeOrders();
    }    
}