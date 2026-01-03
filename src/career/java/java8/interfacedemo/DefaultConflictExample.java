package career.java.java8.interfacedemo;

interface A {
    default void show() {
        System.out.println("A's show()");
    }
}

interface B {
    default void show() {
        System.out.println("B's show()");
    }
}

class D {
    void show(){
        System.out.println("D's show()");
    }
}
class C
//        extends D
        implements A, B {
    @Override
    public void show() {
        A.super.show(); // ❗ Must override → compiler error otherwise
    }
    // Resolving the conflict by overriding the show method
//    @Override
//    public void show() {
//        System.out.println("C's show()");
//    }
}

public class DefaultConflictExample {
    public static void main(String[] args) {
        C c = new C();
        c.show();  // Output: C's show()
        A a =c;
        a.show();
        B b = c;
        b.show();
    }
}
