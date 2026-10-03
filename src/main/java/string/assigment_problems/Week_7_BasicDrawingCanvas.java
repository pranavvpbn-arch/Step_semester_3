import java.util.Scanner;
abstract class Shape{
 private static int counter; private final String shapeId; protected Shape(){shapeId="SH-"+(++counter);}
 public abstract double calculateArea(); public void scale(double f){scale(f,f);} public abstract void scale(double x,double y); public String getShapeId(){return shapeId;}
}
class CircleShape extends Shape{
 private double radius; public CircleShape(double r){if(r<=0)throw new IllegalArgumentException();radius=r;}
 public double calculateArea(){return Math.PI*radius*radius;} public void scale(double x,double y){radius*=x;radius*=y;}
}
class SquareShape extends Shape{
 private double side; public SquareShape(double s){if(s<=0)throw new IllegalArgumentException();side=s;}
 public double calculateArea(){return side*side;} public void scale(double x,double y){side*=x;side*=y;}
}
public class Week_7_BasicDrawingCanvas{
 static void printArea(Shape s){System.out.println("Area: "+s.calculateArea());}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in); System.out.println("Circle radius:"); CircleShape c=new CircleShape(sc.nextDouble());
  System.out.println("Square side:"); SquareShape q=new SquareShape(sc.nextDouble()); System.out.println(c.getShapeId()); printArea(c); System.out.println(q.getShapeId()); printArea(q);
  System.out.println("Square scale factor:"); q.scale(sc.nextDouble()); printArea(q); sc.close();
 }
}
