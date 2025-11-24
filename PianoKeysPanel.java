import javax.swing.*;
import java.awt.*;


public class PianoKeysPanel extends JPanel {

    //declaring the attributes for the width and length of keys within the instrument sequencer
    private int keyWidth = 80;
    private int keyHeight;

    //black keys in order that they are on a piano
    private boolean[] isBlackArray = {false, true, false, true, false, false, true, false, true, false, true, false};

    //constructor for the pianoKeysPanel
    public PianoKeysPanel(int cellHeight) {

        //sets the preferred size to remove any areas during painting
        this.keyHeight = cellHeight;
        setPreferredSize(new Dimension(80, 128 * keyHeight));
    }


    //method for painting the piano keys on the instrument sequencer
    @Override
    protected void paintComponent(Graphics graphics){

        //call parent method
        super.paintComponent(graphics);

        //create the graphics drawer
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //cycle through all the keys
        for(int i = 127; i >= 0; i--) {

            //sets the values for attributes of the keys size and colour
            int row = 127 - i;
            int height = row * keyHeight;
            int semitone = i % 12;
            boolean isBlack = isBlackArray[semitone];

            //draw a black key if isBlack is true
            if(isBlack) {
                graphics2D.setColor(Color.BLACK);
                graphics2D.fillRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.drawRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.WHITE);
            }

            //draw a white key if isBlack is false
            else{
                graphics2D.setColor(Color.WHITE);
                graphics2D.fillRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.GRAY);
                graphics2D.drawRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.BLACK);
            }

            //checks if the note is the twelfth, meaning that it is a C
            //if the note is a C, the letter C is painted onto the key
            if(semitone == 0){
                int octave = (i / 12);
                octave = octave - 1;
                String label = "C" + octave;

                //drawing the "C"
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString(label, 5, height + keyHeight - 5);

            }
        }
        graphics2D.dispose();
    }
}
