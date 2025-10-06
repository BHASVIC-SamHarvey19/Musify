import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
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
    private JProgressBar timelineProgressBar;
    private JComboBox toolsComboBox;
    private JComboBox effectsComboBox;

    private int instrumentButtonCycle = 0;

    private int nextInstrumentSlot = 0;

    private Instrument[] instruments = new Instrument[5];

    private int chosenInstrumentNum;


    public MainGUI() {
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
    }

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

    public void addInstrument(String instrumentName) {
        if(nextInstrumentSlot >= instruments.length) {
            System.out.println("Add instrument failed");
        }
        instruments[nextInstrumentSlot] = new Instrument(chosenInstrumentNum);
        nextInstrumentSlot++;
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setChosenInstrumentNum(int num){
        chosenInstrumentNum = num;
    }

}
