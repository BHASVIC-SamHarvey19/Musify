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
    private JPanel sequencerHoldingPanel;
    private JPanel pianoHoldingPanel;
    private JProgressBar sequencerProgressBar;

    private Instrument instrument;


    public InstrumentSequencer(Instrument instrument) {
        this.instrument = instrument;

        PianoKeysPanel pianoKeysPanel = new PianoKeysPanel(20);
        JScrollPane pianoScroll = new JScrollPane(pianoKeysPanel);
        pianoScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        SequencerGrid sequencerGrid = new SequencerGrid(instrument);
        JScrollPane gridScroll = new JScrollPane(sequencerGrid);
        sequencerGrid.setPreferredSize(new Dimension(1024 * 20, 128 * 20));

        pianoScroll.getVerticalScrollBar().setModel(gridScroll.getVerticalScrollBar().getModel());

        sequencerSplitPane.setLeftComponent(pianoScroll);
        sequencerSplitPane.setRightComponent(gridScroll);
        sequencerSplitPane.setDividerLocation(100);

        sequencerProgressBar.setMinimum(0);
        sequencerProgressBar.setMaximum(1024);
        sequencerProgressBar.setValue(0);

        instrument.setProgressBar(sequencerProgressBar);



        mainSequencerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(mainSequencerPanel);
                window.dispose();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instrument.play(100);
                sequencerProgressBar.setValue(0);
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instrument.isPaused()){
                    instrument.resume();
                }
                else{
                    instrument.pause();
                }
            }
        });
        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instrument.stopAndRewind();
                sequencerProgressBar.setValue(0);
            }
        });
    }

    public JPanel getMainSequencerPanel() {
        return panel1;
    }

}


