import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class instrumentSequencer {
    private JPanel panel1;
    private JPanel mainSequencerPanel;
    private JPanel northPanel;
    private JLabel timeLabel;
    private JPanel playbackPanel;
    private JButton rewindButton;
    private JButton pauseButton;
    private JButton playButton;
    private JButton mainSequencerButton;
    private JScrollPane sequencerScrollPane;
    private JSplitPane sequencerSplitPlane;
    private JPanel pianoKeysPanel;
    private JProgressBar sequencerProgressBar;
    private JPanel sequencerGrid;

    private Instrument instrument;


    public instrumentSequencer(Instrument instrument) {
        this.instrument = instrument;

        pianoKeysPanel pianoKeysPanel = new pianoKeysPanel(20);







        mainSequencerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(mainSequencerPanel);
                window.dispose();
            }
        });
    }

    public JPanel getMainSequencerPanel() {
        return mainSequencerPanel;
    }

}

