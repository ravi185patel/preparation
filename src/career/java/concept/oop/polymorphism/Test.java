package career.java.concept.oop.polymorphism;

/*
🚨 Why This Is a Bad Practice

Object is in partially constructed state

Can cause:
NullPointerException
Incorrect values
Hard-to-find bugs
Breaks encapsulation & safety

❓ If you call an instance method inside a constructor, what happens?
✅ Short Answer
The instance method is executed
But ⚠️ dynamic dispatch (runtime polymorphism) applies
If the method is overridden in a subclass, the subclass method is called
❗ This can be dangerous because the subclass is not fully initialized yet

Execution Order

Memory allocated → x = 0
A() constructor starts
show() called
Runtime dispatch → B.show()
x still 0
B() constructor runs → x = 10


 */
class A {
A() { 
System.out.println("In A constructor...!");
// object is partial constructed and if you call any method it become problem.
    show();

/*
Actual object type = B
So show() → B.show()
*/
}
void show() { 
System.out.println("A show"); 
} 
} 
class B extends A { 
int x = 10; 
void show() { 
System.out.println("B show: " + x); 
} 
} 
public class Test { 
    public static void main(String[] args) {
        new A();
        new B(); // as dynamic dispatch ( method overriding) so called B class even A constructor has show method();
    }
} 