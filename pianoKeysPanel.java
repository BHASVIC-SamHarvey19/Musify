import javax.swing.*;
import java.awt.*;


public class pianoKeysPanel extends JPanel {
    private int rows;
    private int keyWidth = 80;
    private int keyHeight;
    private int topNote;
    private int bottomNote;

    private boolean[] isBlackArray = {false, true, true, false, true, false, true, true, false, true, true, false};

    public pianoKeysPanel(int topNote, int bottomNote, int cellHeight) {
        this.topNote = topNote;
        this.bottomNote = bottomNote;
        this.rows = topNote - bottomNote + 1;
        this.keyHeight = cellHeight;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics.create();

        for(int i = 0; i < rows; i++) {
            int height = i * keyHeight;
            int midi = topNote - i;
            int semiTone = midi % 12;
            boolean isBlack = isBlackArray[(semiTone + 12) % 12];

            if(isBlack) {
                graphics2D.setColor(Color.BLACK);
                graphics2D.fillRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.drawRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.WHITE);
            }
            else{
                graphics2D.setColor(Color.WHITE);
                graphics2D.fillRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.GRAY);
                graphics2D.drawRect(0, height, getWidth(), keyHeight);
                graphics2D.setColor(Color.BLACK);
            }
        }
        graphics2D.dispose();
    }
}
