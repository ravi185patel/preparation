package career.java.concept.oop.polymorphism;

public class PrimitiveOverloading {
    void show(String s) {
        System.out.println("String");
    }
    void show(Object o) {
        System.out.println("Object");
    }
    public static void main(String[] args) {
        PrimitiveOverloading t = new PrimitiveOverloading();
        t.show(null);
        t.show("CodingLyf");
//        Point to remember: Overloading prefers more specific types when multiple matches are possible.
// t.show(null) passes null, which matches both, but String is more specific than Object
    }
}
