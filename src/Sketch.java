import java.awt.*;
import java.util.TreeMap;

/**
 * @author Kashan Mahmood.
 * Created March 21, 2021 for PSET6
 */

public class Sketch {

    private TreeMap<Integer,Shape > shapeMap;  //map to keep track of the shape with their id being the <key> d
                                                //we use this to keep track of the order shapes were drawn
    private int maxId;              //keeps track of maxId which we use when adding a new shape to sketch
                                    // is updated by 1 when a new shape is added

    //constructor
    public Sketch(){

        //declares the instance variables
        shapeMap = new TreeMap<>();
        maxId=0;
    }

    //adds the Shape item to the sketch with Id being maxId, which is then incremented by one
    public void add(Shape item){
        shapeMap.put(maxId, item);
        maxId++;
    }

    //removes shape at <key> id from the sketch
    public void remove(int id){
        shapeMap.remove(id);
    }

    //moves shape at <key> id from the sketch by calling the shape's moveBy method with the correct parameters
    public void move(int id, int dx, int dy){
        shapeMap.get(id).moveBy(dx,dy);
    }

    //recolors shape at <key> id from the sketch by calling the shape's setColor method and using the rgb provided to make the new color
    public void recolor(int id, int rgb){
        shapeMap.get(id).setColor(new Color(rgb));
    }

    //returns shapeMap that contains all the id and the shape in this sketch
    public TreeMap<Integer,Shape > getShapeSketch(){
        return shapeMap;
    }

    //returns the id of the newest shape at point p
    public int topShapeIdAt(Point p){

            //goes through each shape in the sketch, in descending order- so the  newest first
            for(int id: getShapeSketch().descendingKeySet()){

                //the id of newest one that contains the point p is returned
                if(getShapeSketch().get(id).contains(p.x, p.y)){
                    return id;
                }
            }
            //if no shape contains point p, the we just return -1
            return -1;
    }

    //draws the sketch
    public void draw(Graphics g){

        //goes through each shape in the sketch and calls its respective draw method.
        for (int id: shapeMap.navigableKeySet()){
            shapeMap.get(id).draw(g);
        }
    }

    //toString() method that just tells us how many shapes are in this sketch
    public String toString(){
        return "This sketch has " + getShapeSketch().size() + " shapes";
    }
}
