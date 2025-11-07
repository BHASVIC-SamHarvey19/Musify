import javax.sound.midi.*;
import javax.swing.*;
import java.util.Arrays;




public class Note {
    private int pitch;
    private int start;
    private int length;

    public Note(int pitch, int start, int length){
        this.pitch = pitch;
        this.start = start;
        this.length = length;
    }
    public int getPitch() {
        return pitch;
    }
    public int getStart() {
        return start;
    }
    public int getLength() {
        return length;
    }
    public void setPitch(int pitch) {
        this.pitch = pitch;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public void setLength(int length) {
        this.length = length;
    }
}
