

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SequencerGrid extends JPanel {

    //declaring the amount of rows, columns and the size of each gridBlock
    private int rows = 128;
    private int columns = 256;
    private int cellSize = 20;

    //attributes for objects
    private Instrument instrument;
    private Note selectedNote = null;

    //attribute to see if the user is resizing a note
    private boolean resizing = false;

    //constructor for sequencerGrid
    public SequencerGrid(Instrument instrument) {
        this.instrument = instrument;

        //sets preferred size to remove errors during runtime
        setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));

        //mouse listeners for various purposes
        addMouseListener(new MouseAdapter() {

            //when a mouse button is pressed, a note will be added
            @Override
            public void mousePressed(MouseEvent mouse){

                //finding the pitch and timing of the note to be added
                int col = mouse.getX() / cellSize;
                int row = mouse.getY() / cellSize;

                //finding the note that has been selected to be added
                int midiPitch = 127 - row;
                selectedNote = findNoteAt(midiPitch, col);

                //if the note exists
                if(selectedNote != null) {

                    //if they are holding the edge of a note, then resize the note
                    if(col == selectedNote.getStart() + selectedNote.getLength() - 1) {
                        resizing = true;
                    }

                    //if they are holding the note (not the end) then remove the note
                    else{
                        instrument.removeNote(selectedNote);
                        selectedNote = null;
                    }

                }

                //if the note does not exist, then a new note is added
                else if(selectedNote == null) {
                    Note newNote = new Note(midiPitch, col, 1);
                    instrument.addNote(newNote);
                    selectedNote = newNote;
                }

                //the grid is repainted
                repaint();
            }


            //remove the value of selectedNote and set resizing to false when the mouse is released
            @Override
            public void mouseReleased(MouseEvent mouse){
                selectedNote = null;
                resizing = false;
            }
        });

        //resize the note if the note is selected, and the mouse has been dragged
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouse){
                if(selectedNote != null && resizing == true) {
                    int col = mouse.getX() / cellSize;
                    selectedNote.setLength(Math.max(1, col - selectedNote.getStart()) + 1);
                    repaint();
                }
            }
        });


    }

    //method for drawing the whole grid
    //this method is redone after any change to the grid is made
    //this change can be adding/removing/resizing notes
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        //cycle through all the rows and columns
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                int x = j * cellSize;
                int y = i * cellSize;

                //fills a rectangle with white and outlines it with grey
                //this is drawing an empty box on the grid (no note selected)
                graphics.setColor(Color.white);
                graphics.fillRect(x, y, cellSize, cellSize);

                graphics.setColor(Color.gray);
                graphics.drawRect(x, y, cellSize, cellSize);
            }
        }

        //cycles through all notes
        for(Note note : instrument.getNotes()) {

            //draws the note in the correct start and end time as a rectangle in the colour black
            int row = 127 - note.getPitch();
            int x = note.getStart() * cellSize;
            int y = row * cellSize;

            graphics.setColor(Color.black);
            graphics.fillRect(x + 1, y + 1, note.getLength() * cellSize - 1, cellSize - 1);
        }
    }

    //method for finding a note in relation to the pitch and timing of the note
    private Note findNoteAt(int pitch, int col){
        for(Note note : instrument.getNotes()){
            if(note.getPitch() == pitch && col >= note.getStart() && col < note.getStart() + note.getLength()){
                return note;
            }
        }
        return null;
    }
}
