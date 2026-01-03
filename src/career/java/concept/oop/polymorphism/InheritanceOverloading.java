package career.java.concept.oop.polymorphism;

class AO {}
class BO extends AO {}
class InheritanceOverloading {
    void show(AO a) {
        System.out.println("A");
    }
    void show(BO b) {
        System.out.println("B");
    }
    public static void main(String[] args) {
        AO obj = new BO();
        InheritanceOverloading t = new InheritanceOverloading();
        t.show(obj);
    }
}