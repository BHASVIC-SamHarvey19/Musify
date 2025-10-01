import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstrumentSequencer {
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
    private JSplitPane sequencerSplitPane;
    private JProgressBar sequencerProgressBar;
    private JPanel sequencerGrid;
    private JPanel holdingPanel;

    private Instrument instrument;


    public InstrumentSequencer(Instrument instrument) {
        this.instrument = instrument;

        PianoKeysPanel pianoKeysPanel = new PianoKeysPanel(20);
        holdingPanel.setLayout(new BorderLayout());
        holdingPanel.add(pianoKeysPanel, BorderLayout.CENTER);

        JScrollPane pianoScroll = new JScrollPane(pianoKeysPanel);
        pianoScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel dummyGrid = new JPanel();
        dummyGrid.setPreferredSize(new Dimension(800, 2560));
        JScrollPane gridScroll = new JScrollPane(dummyGrid);

        sequencerSplitPane.setLeftComponent(pianoScroll);
        sequencerSplitPane.setRightComponent(gridScroll);
        sequencerSplitPane.setDividerLocation(100);
        mainSequencerPanel.add(sequencerSplitPane, BorderLayout.CENTER);






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

