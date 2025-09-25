import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI {
    private JPanel mainPanel;
    private JPanel playbackPanel;
    private JButton rewindButton;
    private JButton playButton;
    private JButton pauseButton;
    private JLabel timeLabel;
    private JPanel northPanel;
    private JPanel timelinePanel;
    private JPanel southPanel;
    private JButton instrument1;
    private JButton instrument2;
    private JButton instrument3;
    private JButton instrument4;
    private JButton instrument5;
    private JPanel instrument1Timeline;
    private JPanel instrument2Timeline;
    private JPanel instrument3Timeline;
    private JPanel instrument4Timeline;
    private JPanel instrument5Timeline;
    private JProgressBar timelineProgressBar;
    private JLabel instrument1Label;
    private JLabel instrument2Label;
    private JLabel instrument3Label;
    private JLabel instrument4Label;
    private JLabel instrument5Label;
    private JComboBox toolsComboBox;
    private JComboBox effectsComboBox;




    public static void main(String[] args) {
        JFrame frame = new JFrame("mainGUI");
        frame.setContentPane(new mainGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
