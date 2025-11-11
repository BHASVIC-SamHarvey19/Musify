

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SequencerGrid extends JPanel {
    private int rows = 128;
    private int columns = 256;
    private int cellSize = 20;

    private Instrument instrument;
    private Note selectedNote = null;
    private boolean resizing = false;

    public SequencerGrid(Instrument instrument) {
        this.instrument = instrument;

        setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouse){
                int col = mouse.getX() / cellSize;
                int row = mouse.getY() / cellSize;

                int midiPitch = 127 - row;

                selectedNote = findNoteAt(midiPitch, col);

                if(selectedNote != null) {
                    if(col == selectedNote.getStart() + selectedNote.getLength() - 1) {
                        resizing = true;
                    }
                    else{
                        instrument.removeNote(selectedNote);
                        selectedNote = null;
                    }

                }
                else if(selectedNote == null) {
                    Note newNote = new Note(midiPitch, col, 1);
                    instrument.addNote(newNote);
                    selectedNote = newNote;
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouse){
                selectedNote = null;
                resizing = false;
            }
        });

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
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                int x = j * cellSize;
                int y = i * cellSize;

                graphics.setColor(Color.white);
                graphics.fillRect(x, y, cellSize, cellSize);

                graphics.setColor(Color.gray);
                graphics.drawRect(x, y, cellSize, cellSize);
            }
        }
        for(Note note : instrument.getNotes()) {
            int row = 127 - note.getPitch();
            int x = note.getStart() * cellSize;
            int y = row * cellSize;

            graphics.setColor(Color.black);
            graphics.fillRect(x + 1, y + 1, note.getLength() * cellSize - 1, cellSize - 1);
        }
    }

    private Note findNoteAt(int pitch, int col){
        for(Note note : instrument.getNotes()){
            if(note.getPitch() == pitch && col >= note.getStart() && col < note.getStart() + note.getLength()){
                return note;
            }
        }
        return null;
    }
}
