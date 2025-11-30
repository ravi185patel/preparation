package career.java.concept.oop;

interface  InheritanceD{
    int i=0;
    void find();
    int get();
}

class InheritanceDImp implements InheritanceD{

    public void find(){
        System.out.println("find child");
    }
    public int get(){ // float = premitive return type not supported.
        System.out.println("get child");
        return 1;
    }

    public void method(int i,float j){
        System.out.println("int + float");
    }

    public void method(float i,int j){
        System.out.println("float + int");
    }
}


public class InheritanceDemo {
    public static void main(String[] args) {
        InheritanceDImp inheritanceDImp = new InheritanceDImp();
        inheritanceDImp.method(1,1f);
        inheritanceDImp.method(1f,1);
//        inheritanceDImp.method(1,1);   //ambiguity second argument convert int into float
//        inheritanceDImp.method(1f,1f); //ambiguity first argument convert
    }
}
