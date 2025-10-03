

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SequencerGrid extends JPanel {
    private int rows = 128;
    private int columns = 1024;
    private int cellSize = 20;

    private Instrument instrument;

    public SequencerGrid(Instrument instrument) {
        this.instrument = instrument;

        setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouse){
                int col = mouse.getX() / cellSize;
                int row = mouse.getY() / cellSize;

                if(col >= 0 && col < columns && row >= 0 && row < rows) {

                    int midiNote = 127 - row;

                    if(midiNote >= 0 && midiNote < rows) {
                        if (instrument.getNoteState(midiNote, col) == 0) {
                            instrument.addNote(midiNote, col);
                        } else {
                            instrument.removeNote(midiNote, col);
                        }
                        repaint();
                    }
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

                int midiNote = 127 - i;
                if(midiNote >= 0 && midiNote < rows) {
                    if (instrument.getNoteState(midiNote, j) == 1) {
                        graphics.setColor(Color.black);
                        graphics.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
                    }
                }
            }
        }
    }
}
