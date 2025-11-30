package career.java.concept.oop;

abstract class Demo{
    int i;
    abstract int get();
    public void print(){
        System.out.println("print");
    }
    final public void finalMethod(){
        System.out.println("final method");
    }
    protected void protectedMethod(){
        System.out.println("protected method");
    }
    private void privateMethod(){ System.out.println("private method"); }
    void defaultMethod(){ System.out.println("default method"); }
    public Demo(){
        System.out.println("Demo");
    }
    public Demo(int i){
        System.out.println("Demo 1");
    }
}

class DemoImp extends  Demo{
    public DemoImp(int i){
        super(i);
        System.out.println("DemoImp");
    }
    public int get(){
        return 1;
    }

//    public void finalMethod(){System.out.println("final method");} // final method can't override

    public void protectedMethod(){System.out.println("protected method child");} // overriding  you can increase it
//    private void protectedMethod(){ System.out.println("protected method child"); } // not worked you can reduce access ability
    private void privateMethod(){ System.out.println("private method child"); } //
    void defaultMethod(){ System.out.println("default method child"); } //overriding
}

public class AbsrtactClass {
    public static void main(String[] args) {
        Demo demo =  new DemoImp(1);
        int i =demo.get();
        System.out.println(i);
        demo.protectedMethod();
        demo.defaultMethod();

//        Demo d =  new Demo(1); // can't initialize abstract method

//        demo.privateMethod() // can access within class.
    }
}
