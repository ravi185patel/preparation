package career.designpattern.gofdesignpattern.creationalds.prototype;

import java.util.*;
abstract class Color implements Cloneable{
    protected String color;
    abstract void addColor();
    public Object clone(){
        Object clone=null;
        try{
            clone=super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }
}
class BlueColor extends Color{

    public BlueColor(){
        this.color="blue";
    }
    @Override
    void addColor() {
        System.out.println("added Blue color");
    }
    
}
class BlackColor extends Color{

    public BlackColor(){
        this.color="black";
    }
    @Override
    void addColor() {
        System.out.println("added black color");
    }
    
}
class ColorStore{ // registory
    private static Map<String,Color> colorMap=new HashMap<String,Color>();

    static{
        colorMap.put("blue",new BlueColor());
        colorMap.put("black",new BlackColor());
    }
    public static Color getColor(String name){
        return (Color) colorMap.get(name).clone();
    }
}
public class PrototypeDemo {
    public static void main(String[] args) {
        ColorStore.getColor("blue").addColor();
        ColorStore.getColor("black").addColor();
        ColorStore.getColor("blue").addColor();
        System.out.println(ColorStore.getColor("blue").hashCode()+" "+ColorStore.getColor("blue").hashCode());
    }
}