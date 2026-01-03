package career.java.concept.oop.polymorphism;

public class AutoboxingVsWideningInOverrriding {
    void show(Integer i) {
        System.out.println("Integer");
    }
    void show(long l) {
        System.out.println("long");
    }
    public static void main(String[] args) {
        AutoboxingVsWideningInOverrriding t = new AutoboxingVsWideningInOverrriding();
        t.show(10);
//         Point to Remember: Java prefers widening over boxing during overload resolution.
    }
}
