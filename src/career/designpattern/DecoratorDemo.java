package career.designpattern;

import java.util.Comparator;

interface Food{
    public String prFood();
    public int foodPrice();
} 
class VegFood implements Food{
    private String name;
    public VegFood(){
        name="veg food";
    }

    @Override
    public String prFood() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public int foodPrice() {
        return 12;
    }
}
abstract class FoodDecoratro implements Food{
    private Food food;
    public FoodDecoratro(Food f){
        food=f;
    }
    public String prFood(){
        return food.prFood();
    }
    public int foodPrice(){
        return food.foodPrice();
    }
}
class NonVeg extends FoodDecoratro{

    public NonVeg(Food f) {
        super(f);
    }

    public String prFood(){
        return super.prFood()+" non veg";
    }
    public int foodPrice(){
        return super.foodPrice()+100;
    }
}
class ChinesVeg extends FoodDecoratro{

    public ChinesVeg(Food f) {
        super(f);
    }

    public String prFood(){
        return this.prFood()+" non veg";
    }
    public int foodPrice(){
        return this.foodPrice()+100;
    }
     
}
@FunctionalInterface
interface Demm extends Comparator<String>{
	
}
public class DecoratorDemo {
    public static void main(String[] args) {
        // used in synchronizaedList(),SynchronizedXX() method. InputStream outputStream parameter.
        System.out.println("dec p");
        VegFood v=new VegFood();
        NonVeg n=new NonVeg(v); // veg food converted into non-veg
        System.out.println(n.prFood()+" "+n.foodPrice());
        
    }
}