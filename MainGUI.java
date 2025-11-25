import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    //declaring all the components of the GUI
    private JPanel mainPanel;
    private JPanel playbackPanel;
    private JButton rewindButton;
    private JButton playButton;
    private JButton pauseButton;
    private JPanel northPanel;
    private JPanel timelinePanel;
    private JPanel southPanel;
    private JButton instrument1;
    private JButton instrument2;
    private JButton instrument3;
    private JButton instrument4;
    private JButton instrument5;
    private JProgressBar timelineProgressBar;
    private JComboBox toolsComboBox;
    private JComboBox effectsComboBox;

    //sets the instrument button cycle to the 0th instrument
    private int instrumentButtonCycle = 0;

    //sets the next available instrument slot to 0
    private int nextInstrumentSlot = 0;

    //array of instruments in the sequencer
    private Instrument[] instruments = new Instrument[5];

    //integer attribute for the chosen instrument to be added
    private int chosenInstrumentNum;


    public MainGUI() {

        //action listener to open the addInstrumentForm if that is selected
        //after the form has been opened, the value in the JComboBox back to "Tools"
        toolsComboBox.addActionListener(e -> {
                if("Add Instrument".equals(toolsComboBox.getSelectedItem())) {
                    toolsComboBox.setSelectedIndex(0);

                    JFrame frame = new JFrame("Add Instrument");
                    frame.setContentPane(new AddInstrumentForm(this).getAddInstrumentPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
        });

        //if the first instrument button is pressed, open the individual sequencer for that instrument
        instrument1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instruments[0] != null) {
                    JFrame frame = new JFrame("Instrument 1");
                    frame.setContentPane(new InstrumentSequencer(instruments[0]).getMainSequencerPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                }
            }
        });

        //if the second instrument button is pressed, open the individual sequencer for that instrument
        instrument2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instruments[1] != null) {
                    JFrame frame = new JFrame("Instrument 2");
                    frame.setContentPane(new InstrumentSequencer(instruments[1]).getMainSequencerPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                }
            }
        });

        //if the third instrument button is pressed, open the individual sequencer for that instrument
        instrument3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instruments[2] != null) {
                    JFrame frame = new JFrame("Instrument 3");
                    frame.setContentPane(new InstrumentSequencer(instruments[2]).getMainSequencerPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);




                }
            }
        });

        //if the fourth instrument button is pressed, open the individual sequencer for that instrument
        instrument4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instruments[3] != null) {
                    JFrame frame = new JFrame("Instrument 4");
                    frame.setContentPane(new InstrumentSequencer(instruments[3]).getMainSequencerPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });

        //if the fifth instrument button is pressed, open the individual sequencer for that instrument
        instrument5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instruments[4] != null) {
                    JFrame frame = new JFrame("Instrument 5");
                    frame.setContentPane(new InstrumentSequencer(instruments[4]).getMainSequencerPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                }
            }
        });

        //listener for the play button
        //opens 5 threads for all the instruments with their play method inside
        //if the instrument exists, then the thread is opened and started
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread playThread1 = new Thread(() -> {instruments[0].play(100);});
                Thread playThread2 = new Thread(() -> {instruments[1].play(100);});
                Thread playThread3 = new Thread(() -> {instruments[2].play(100);});
                Thread playThread4 = new Thread(() -> {instruments[3].play(100);});
                Thread playThread5 = new Thread(() -> {instruments[4].play(100);});
                if(instruments[0] != null) {
                    playThread1.start();
                }
                if(instruments[1] != null) {
                    playThread2.start();
                }
                if(instruments[2] != null) {
                    playThread3.start();
                }
                if(instruments[3] != null) {
                    playThread4.start();
                }
                if(instruments[4] != null) {
                    playThread5.start();
                }
            }
        });

        //listener for the pause button
        //pauses all the instruments if they exist
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instruments[0] != null) {
                    instruments[0].pause();
                }
                if(instruments[1] != null) {
                    instruments[1].pause();
                }
                if(instruments[2] != null) {
                    instruments[2].pause();
                }
                if(instruments[3] != null) {
                    instruments[3].pause();
                }
                if(instruments[4] != null) {
                    instruments[4].pause();
                }

            }
        });

        //listener for the rewind button
        //stops and rewinds the instrument if the instrument exists (for all instruments)
        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(instruments[0] != null) {
                    instruments[0].stopAndRewind();
                }
                if(instruments[1] != null) {
                    instruments[1].stopAndRewind();
                }
                if(instruments[2] != null) {
                    instruments[2].stopAndRewind();
                }
                if(instruments[3] != null) {
                    instruments[3].stopAndRewind();
                }
                if(instruments[4] != null) {
                    instruments[4].stopAndRewind();
                }
            }
        });

        //listener for the effectsComboBox
        //opens the reverb or chorus form if that has been selected
        //after the form has been opened, the effectsComboBox is set back to "effects"
        effectsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Reverb".equals(effectsComboBox.getSelectedItem())) {
                    JFrame frame = new JFrame("Reverb Effects Implementer");
                    frame.setContentPane(new Reverb(MainGUI.this).getRootPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    effectsComboBox.setSelectedItem("Effects");
                }
                if("Chorus".equals(effectsComboBox.getSelectedItem())) {
                    JFrame frame = new JFrame("Chorus Effects Implementer");
                    frame.setContentPane(new Chorus(MainGUI.this).getRootPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    effectsComboBox.setSelectedItem("Effects");
                }
            }
        });
    }

    //sets the button for the instrument's individual instrument sequencer to the correct text based on the instrument that has been chosen to be added
    public void setInstrumentButton(String instrumentName){
        if(instrumentButtonCycle == 0) {
            instrument1.setText(instrumentName);
        }
        else if(instrumentButtonCycle == 1){
            instrument2.setText(instrumentName);
        }
        else if(instrumentButtonCycle == 2){
            instrument3.setText(instrumentName);
        }
        else if(instrumentButtonCycle == 3){
            instrument4.setText(instrumentName);
        }
        else if(instrumentButtonCycle == 4){
            instrument5.setText(instrumentName);
        }
        instrumentButtonCycle++;
    }

    //adds an instrument if there are available spaces
    public void addInstrument(String instrumentName) {
        if(nextInstrumentSlot >= instruments.length) {
            System.out.println("Add instrument failed");
        }
        instruments[nextInstrumentSlot] = new Instrument(chosenInstrumentNum);
        nextInstrumentSlot++;
    }

    //getter for the root panel
    public JPanel getMainPanel() {
        return mainPanel;
    }

    //setter for chosenInstrumentNum
    public void setChosenInstrumentNum(int num){
        chosenInstrumentNum = num;
    }

    //getter for an instrument in the instruments array
    public Instrument getInstrument(int instrumentNum){
        return instruments[instrumentNum];
    }

}
