import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

    private int instrumentButtonCycle = 0;

    private int nextInstrumentSlot = 0;

    private Instrument[] instruments = new Instrument[5];

    private int chosenInstrumentNum;


    public mainGUI() {
        toolsComboBox.addActionListener(e -> {
                if("Add Instrument".equals(toolsComboBox.getSelectedItem())) {
                    toolsComboBox.setSelectedIndex(0);

                    JFrame frame = new JFrame("Add Instrument");
                    frame.setContentPane(new addInstrumentForm(this).getAddInstrumentPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
        });
        instrument1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Instrument 1");
                frame.setContentPane(new instrumentSequencer(instruments[0]).getMainSequencerPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
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
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setChosenInstrumentNum(int num){
        chosenInstrumentNum = num;
    }







}
