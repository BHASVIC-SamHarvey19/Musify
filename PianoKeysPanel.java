import javax.swing.*;
import java.awt.*;


public class PianoKeysPanel extends JPanel {
    private int keyWidth = 80;
    private int keyHeight;

    private boolean[] isBlackArray = {false, true, true, false, true, false, true, true, false, true, true, false};

    public PianoKeysPanel(int cellHeight) {
        this.keyHeight = cellHeight;
        setPreferredSize(new Dimension(80, 128 * keyHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(int i = 127; i >= 0; i--) {
            int row = 127 - i;
            int height = row * keyHeight;
            int semitone = i % 12;
            boolean isBlack = isBlackArray[(semitone + 12) % 12];

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
