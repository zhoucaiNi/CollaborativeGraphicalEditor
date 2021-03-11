import java.awt.*;
/**
 * @author Kashan Mahmood.
 * Created March 21, 2021 for PSET6
 */

public class Message {
    private String msg;         //keeps track of the message passed
    private Sketch sketch;      //keeps track of the sketch we will be making changes to

    //if the message involves a server, use this constructor
    public Message(String msg, SketchServer server) {
        //save the message
        this.msg = msg;

        //save the sketch of the server as an instance variable for later use
        sketch= server.getSketch();
    }

    //if the message involves an editor, we use this constructor
    public Message(String msg, Editor editor) {
        //save the message
        this.msg = msg;

        //save the sketch of the editor as an instance variable for later use
        sketch= editor.getSketch();
    }

    //this processes the request made in the message
    public void process() {

        //breaks the message up by empty spaces and stores it in an array
        String[] request = msg.split(" ");

        //depending on the first word of the message, call the respctive helped methods to help process the request

        if (request[0].equals("draw")) {
            add(request);
        } else if (request[0].equals("delete")) {
            remove(request);
        } else if (request[0].equals("recolor")) {
            recolor(request);
        } else if (request[0].equals("move")) {
            move(request);
        }
        else if(request[0].equals("setID")){
            int Id = Integer.parseInt(request[1]);
            sketch.setMaxId(Id + 1);
        }

    }


    //helped method for when the request is to add shape
    public synchronized void add(String[] msg) {
        //depending on the type of object we are requested to add,
        //use the respective parts of the msg array to construct the new shape and add it to the sketch

        if (msg[1].equals("ellipse")) {
            sketch.add(new Ellipse(Integer.parseInt(msg[2]),
                    Integer.parseInt(msg[3]), Integer.parseInt(msg[4]),
                    Integer.parseInt(msg[5]), new Color(Integer.parseInt(msg[6]))));
        } else if (msg[1].equals("rectangle")) {
            sketch.add(new Rectangle(Integer.parseInt(msg[2]),
                    Integer.parseInt(msg[3]), Integer.parseInt(msg[4]),
                    Integer.parseInt(msg[5]), new Color(Integer.parseInt(msg[6]))));
        } else if (msg[1].equals("segment")) {
            sketch.add(new Segment(Integer.parseInt(msg[2]),
                    Integer.parseInt(msg[3]), Integer.parseInt(msg[4]),
                    Integer.parseInt(msg[5]), new Color(Integer.parseInt(msg[6]))));
        }
    }

    //helped method for removing a shape from the sketch
    public synchronized void remove(String[] msg) {
        //index 1 gives the Id of the objected that is requested to be removed.
        //turn the string into an integer and call the remove method in sketch to remove
        //the shape at id value of map of shapes in sketch.
        sketch.remove(Integer.parseInt(msg[1]));
    }

    //helped method for recoloring a shape in sketch
    public synchronized void recolor(String[] msg) {
        //index 1 gives the Id of the objected that is requested to be recolor. the new color's rgb is at index 2
        //turn the strings into an integer and call the recolor method in sketch with the respective parameters.
        sketch.recolor(Integer.parseInt(msg[1]), Integer.parseInt(msg[2]));
    }

    //helped method for moving a shape in sketch
    public synchronized void move(String[] msg) {
        //index 1 gives the Id of the objected that is requested to be recolor. the dx is at index 2 and dy is at index 3
        //turn the strings into an integer and call the move method in sketch with the respective parameters.
        sketch.move(Integer.parseInt(msg[1]),
                Integer.parseInt(msg[2]), Integer.parseInt(msg[3]));
    }

}


