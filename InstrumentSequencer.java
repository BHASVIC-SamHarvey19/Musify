import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstrumentSequencer {

    //declaring attributes for items on the UI
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

    //constructor for the instrumentSequencer class
    public InstrumentSequencer(Instrument instrument) {
        this.instrument = instrument;

        //Creates the piano on the left side of the sequencer and adds it to a scroll panel
        PianoKeysPanel pianoKeysPanel = new PianoKeysPanel(20);
        JScrollPane pianoScroll = new JScrollPane(pianoKeysPanel);
        pianoScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //creates the sequencer grid and adds it to the scroll panel
        SequencerGrid sequencerGrid = new SequencerGrid(instrument);
        JScrollPane gridScroll = new JScrollPane(sequencerGrid);
        sequencerGrid.setPreferredSize(new Dimension(1024 * 20, 128 * 20));
        
        pianoScroll.getVerticalScrollBar().setModel(gridScroll.getVerticalScrollBar().getModel());

        //adds both items to a JSplitPane
        sequencerSplitPane.setLeftComponent(pianoScroll);
        sequencerSplitPane.setRightComponent(gridScroll);
        sequencerSplitPane.setDividerLocation(100);

        //sets values for the progress bar which shows how far into the sequence has been played
        sequencerProgressBar.setMinimum(0);
        sequencerProgressBar.setMaximum(256);
        sequencerProgressBar.setValue(0);

        //sets the progress bar for the time into music as the correct progress bar
        instrument.setProgressBar(sequencerProgressBar);



        //listener for the button to bring you back to the main sequencer
        mainSequencerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(mainSequencerPanel);
                window.dispose();
            }
        });

        //listener for the play button to play the must from the start
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instrument.play(100);
                sequencerProgressBar.setValue(0);
            }
        });

        //listener for the pause button to resume if it is paused or pause if it is playing
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

        //listener for the rewind button to stop and rewind the music
        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instrument.stopAndRewind();
                sequencerProgressBar.setValue(0);
            }
        });
    }

    //getter for the root panel
    public JPanel getMainSequencerPanel() {
        return panel1;
    }

}


