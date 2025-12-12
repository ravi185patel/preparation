package career.designpattern.gofdesignpattern.structuralds.adapter;

interface ShapeDemo{
    public void draw(int x, int y, int z, int j);
}
class Line{
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("Line from point A(" + x1 + ";" + y1 + "), to point B(" + x2 + ";" + y2 + ")");
    }
}
class Rectangle{
    public void draw(int x, int y, int width, int height) {
        System.out.println("Rectangle with coordinate left-down point (" + x + ";" + y + "), width: " + width
                + ", height: " + height);
    }
}
class LineAdapter implements ShapeDemo{
    private Line adapter;
    public LineAdapter(Line line){
        this.adapter=line;
    }
    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        this.adapter.draw(x1,y1,x2,y2);
    }
    
}
class RectangleAdapter implements ShapeDemo{
    private Rectangle adapter;
    public RectangleAdapter(Rectangle rectangle){
        this.adapter=rectangle;
    }
    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        adapter.draw(x, y, width, height);
    }
}
public class AdapterDemo {
    public static void main(String[] args) {
        ShapeDemo[] shapes = {new RectangleAdapter(new Rectangle()),
                            new LineAdapter(new Line())};
        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;
        for (ShapeDemo shape : shapes) {
            shape.draw(x1, y1, x2, y2);
        }
    }
}