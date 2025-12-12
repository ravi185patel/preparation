package career.designpattern.gofdesignpattern.structuralds.facade;

interface Shape{
    public String draw();
}
class Circle implements Shape{

    @Override
    public String draw() {
        return "circle";
    }
    
}
class Square implements Shape{

    @Override
    public String draw() {
        return "square";
    }
    
}
class ShapeCreator{
    private Shape shape;
    public String draw(){
        return this.shape.draw();
    }
    public void setShape(Shape shape){
        this.shape=shape;
    }
}
public class FacadDemo {
    public static void main(String[] args) {
        Circle c=new Circle();
        Square s=new Square();
        ShapeCreator sp=new ShapeCreator();
        sp.setShape(c);
        System.out.println(sp.draw());
        sp.setShape(s);
        System.out.println(sp.draw());
    }
}