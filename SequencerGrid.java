

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SequencerGrid extends JPanel {
    private int rows = 128;
    private int columns = 2056;
    private int cellSize = 20;
    private boolean[][] grid;

    private Instrument instrument;

    public SequencerGrid(Instrument instrument) {
        this.instrument = instrument;
        this.grid = new boolean[rows][columns];

        setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouse){
                int col = mouse.getX() / cellSize;
                int row = mouse.getY() / cellSize;



                if(col >= 0 && col < columns && row >= 0 && row < rows) {
                    grid[row][col] = !grid[row][col];

                    int midiNote = 127 - row;
                    if(grid[row][col]) {
                        instrument.addNote(midiNote, col);
                    }
                    else{
                        instrument.removeNote(midiNote, col);
                    }
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

                if(grid[i][j]){
                    graphics.setColor(Color.black);
                    graphics.fillRect(x, y, cellSize, cellSize);
                }
                graphics.setColor(Color.gray);
                graphics.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
}
