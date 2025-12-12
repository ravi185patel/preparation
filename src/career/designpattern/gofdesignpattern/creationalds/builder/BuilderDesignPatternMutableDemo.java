package career.designpattern.gofdesignpattern.creationalds.builder;

/*
https://refactoring.guru/design-patterns/builder/java/example#example-0--components-Engine-java
 */

/*
  Here builder is not tightly coupled with object as you can add or change builder
  but as you add new properties in object then it will issue in builder as constructor need to change in builder class.

 */

class Engine{

}

class Transmission{

}

class Vehicle {
    private final Engine engine;
    private final Transmission transmission;

    public Vehicle(Engine engine, Transmission transmission) {
        this.engine = engine;
        this.transmission = transmission;
    }
}


interface Builder{
    public void setEngin(Engine engin);
    public void setTransmission(Transmission transmission);
}

class CarBuilder implements  Builder{

    private Engine engine;
    private  Transmission transmission;

    @Override
    public void setEngin(Engine engin) {
        this.engine=engin;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Vehicle builder(){
        return new Vehicle(engine,transmission);
    }
}

class TruckBuilder implements Builder{
    private Engine engine;
    private  Transmission transmission;

    @Override
    public void setEngin(Engine engin) {
        this.engine=engin;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Vehicle builder(){
        return new Vehicle(engine,transmission);
    }
}

class Company{
    public void createSmallCar(Builder builder){
        builder.setEngin(new Engine());
        builder.setTransmission(new Transmission());
    }

    public void createBigCar(Builder builder){
        builder.setEngin(new Engine());
        builder.setTransmission(new Transmission());
    }

    public void createTruck(Builder builder){
        builder.setEngin(new Engine());
        builder.setTransmission(new Transmission());
    }
}

public class BuilderDesignPatternMutableDemo {

    public static void main(String[] args) {
        Company company = new Company();
        CarBuilder builder = new CarBuilder();
        company.createSmallCar(builder);

        Vehicle vehicle = builder.builder();
        System.out.println("Car built:\n" + vehicle);


        TruckBuilder truckBuilder = new TruckBuilder();

        // Director may know several building recipes.
        company.createTruck(truckBuilder);
        vehicle = builder.builder();
        System.out.println("\nCar manual built:\n" + vehicle);
    }
}
