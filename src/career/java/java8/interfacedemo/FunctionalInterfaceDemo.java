package career.java.java8.interfacedemo;
/*
3. Functional Interfaces

A functional interface is an interface with just one abstract method (but can have multiple default and static methods). Functional interfaces are the foundation of lambda expressions in Java 8.

Common Examples:

Runnable

Callable

Comparator

Consumer

Function

Predicate
 */
@FunctionalInterface
interface FunctionalInterfaceDemo {
    void abstractMethod();  // single abstract method
}

