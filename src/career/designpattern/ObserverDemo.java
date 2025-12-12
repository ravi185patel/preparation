package career.designpattern;

import java.util.ArrayList;
interface Observer{
    public void update(float interestRate);
}
interface Subject{
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
class Loan implements Subject{
    private ArrayList<Observer> observers=new ArrayList<Observer>();
    private String type;
    private float interest;
    private String bank;

    public Loan(String type, float interest, String bank) {
        this.type = type;
        this.interest = interest;
        this.bank = bank;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
        notifyObservers();
    }

    public String getBank() {
        return this.bank;
    }

    public String getType() {
            return this.type;
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println("notify Observer");
        for(Observer ob:observers){
            
            ob.update(this.interest);
        }
    }
}
class NewsPaper implements Observer{

    @Override
    public void update(float interestRate) {
        System.out.println("news paper "+interestRate);
    }
    
}
class Internate implements Observer{

    @Override
    public void update(float interestRate) {
        System.out.println("Internet "+interestRate);
    }
    
}
public class ObserverDemo {
    public static void main(String[] args) {
        NewsPaper newsPaper=new NewsPaper();
        Internate internate=new Internate();
        Loan personLoan=new Loan("personal Loan",12.5f,"standard");
        personLoan.registerObserver(newsPaper);    
        personLoan.registerObserver(internate);
        personLoan.setInterest(14.5f);  
    }
}