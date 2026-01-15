package career.java.java8.interfacedemo;

interface P1{
    void get();
    default void put(){
        System.out.println("Parent put");
    }
}

abstract class AB1 implements P1{
    public abstract void get();
}

class ImplementAB1 extends AB1 implements P1{
    public void get(){
        System.out.println("get");
    }
}

class ImplementAB2 extends AB1{
    public void get(){
        System.out.println("get");
    }
}

class ImplementAB3 implements P1{
    public void get(){
        System.out.println("get");
    }
}
public class InterfaceConcept {
    public static void main(String args[]){
        ImplementAB1 implementAB1 = new ImplementAB1();
        implementAB1.get();

        P1 p1 = new ImplementAB1();
        p1.get();

        AB1 ab1 = new ImplementAB1();
        ab1.get();

        p1 = new ImplementAB2(); // work because P1 is parent of AB1 and AB1 is parent of AB2
        p1.get();

        ab1 = new ImplementAB2();
        ab1.get();

        p1 = new ImplementAB3();
        p1.get();

//        ab1 = new ImplementAB3(); // because AB1 is not parent of AB3
//        ab1.get();

    }
}
