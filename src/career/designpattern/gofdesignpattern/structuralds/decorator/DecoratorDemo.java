package career.designpattern.gofdesignpattern.structuralds.decorator;

interface Food{
    void prepareFood();
    double foodPrice();

}

class VegFood implements Food{


    @Override
    public void prepareFood() {
        System.out.println("veg food");
    }

    @Override
    public double foodPrice() {
        return 0;
    }

}

abstract class FoodDecorator implements Food{
    private Food newFood;
    public FoodDecorator(Food newFood)  {
        this.newFood=newFood;
    }

}

class NonVegFood extends FoodDecorator{

    public NonVegFood(Food newFood) {
        super(newFood);
    }

    @Override
    public void prepareFood() {
        System.out.println("Non-veg food");
    }

    @Override
    public double foodPrice() {
        return 0;
    }


}
class ChinessFood extends FoodDecorator {

    public ChinessFood(Food newFood) {
        super(newFood);
    }

    @Override
    public void prepareFood() {
        System.out.println("chiness food");
    }

    @Override
    public double foodPrice() {
        return 0;
    }

}

public class DecoratorDemo {
    public static void main(String[] args) {
        VegFood vf=new VegFood();
        vf.prepareFood();
        System.out.println( vf.foodPrice());

        Food f1=new NonVegFood((Food) new VegFood());
        f1.prepareFood();
        System.out.println( f1.foodPrice());

        Food f2=new ChinessFood((Food) new VegFood());
        f2.prepareFood();
        System.out.println( f2.foodPrice());
    }
}
