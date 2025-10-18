package career.java.java25;

class Parent {
    Parent(int x) {
        System.out.println("Parent: x = " + x);
    }
}

class Child extends Parent {
    int y;
    public Child(int x, int y) {
        super(x);//
//        if (x < 0) x = 0;  // validation before super
//        super(x);
        this.y = (y >= 0 ? y : 0);
        System.out.println("Child: y = " + this.y);
    }

    public static void main(String[] args) {
        new Child(-5, -10);
    }
}
