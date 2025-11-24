import javax.sound.midi.*;
import javax.swing.*;
import java.util.Arrays;




public class Note {

    //declaring note related attributes
    private int pitch;
    private int start;
    private int length;

    //constructor for Note class
    //created a note object
    public Note(int pitch, int start, int length){
        this.pitch = pitch;
        this.start = start;
        this.length = length;
    }

    //getter for pitch attribute
    public int getPitch() {
        return pitch;
    }

    //getter for start attribute
    public int getStart() {
        return start;
    }

    //getter for length attribute
    public int getLength() {
        return length;
    }

    //setter for pitch attribute
    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    //setter for start attribute
    public void setStart(int start) {
        this.start = start;
    }

    //setter for length attribute
    public void setLength(int length) {
        this.length = length;
    }
}
