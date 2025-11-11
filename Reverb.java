import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reverb {
    private JPanel reverbPanel;
    private JPanel toolsEffectsPanel;
    private JPanel applyReverbPanel;
    private JLabel reverbLabel;
    private JPanel reverbTypePanel;
    private JPanel reverbInstrumentPanel;
    private JPanel reverbStrengthPanel;
    private JPanel reverbLengthPanel;
    private JButton applyReverbButton;
    private JSlider reverbStrengthSlider;
    private JSlider reverbLengthSlider;
    private JRadioButton chamberRadioButton;
    private JRadioButton hallRadioButton;
    private JRadioButton shimmerRadioButton;
    private JRadioButton digitalRadioButton;
    private JRadioButton roomRadioButton;
    private JLabel reverbStrengthLabel;
    private JLabel reverbLengthLabel;
    private JLabel reverbInstrumentLabel;
    private JRadioButton instrument1RadioButton;
    private JRadioButton instrument2RadioButton;
    private JRadioButton instrument3RadioButton;
    private JRadioButton instrument4RadioButton;
    private JRadioButton instrument5RadioButton;
    private JRadioButton instrument1RemoveReverb;
    private JRadioButton instrument2RemoveReverb;
    private JRadioButton instrument3RemoveReverb;
    private JRadioButton instrument4RemoveReverb;
    private JButton reverbRemovalButton;
    private JRadioButton instrument5RemoveReverb;
    private JLabel reverbRemovalLabel;
    private JPanel reverbRemovalPanel;


    private String reverbType;
    private int reverbLength;
    private int reverbStrength;

    private int instrumentNum;

    private int removingInstrumentNum;

    private MainGUI mainGUI;

    public Reverb(MainGUI mainGUI){

        this.mainGUI = mainGUI;

        reverbLengthSlider.setMaximum(5);
        reverbLengthSlider.setMinimum(0);
        reverbStrengthSlider.setMaximum(100);
        reverbStrengthSlider.setMinimum(0);

        reverbLengthSlider.setValue(reverbLength);
        reverbStrengthSlider.setValue(reverbStrength);

        reverbLength = 0;
        reverbStrength = 0;
        reverbType = "";




        reverbLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setReverbLength(reverbLengthSlider.getValue());
                reverbLengthLabel.setText("Reverb Length (" + reverbLengthSlider.getValue() + " seconds)");
            }
        });
        reverbStrengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setReverbStrength(reverbStrengthSlider.getValue());
                reverbStrengthLabel.setText("Reverb Strength (" + reverbStrengthSlider.getValue() + "%)");
            }
        });
        hallRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReverbType("Hall");
            }
        });
        shimmerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReverbType("Shimmer");
            }
        });
        digitalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReverbType("Digital");
            }
        });
        roomRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReverbType("Room");
            }
        });
        chamberRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReverbType("Chamber");
            }
        });
        instrument1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setApplyingInstrument(1);
            }
        });
        instrument2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setApplyingInstrument(2);
            }
        });
        instrument3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setApplyingInstrument(3);
            }
        });
        instrument4RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setApplyingInstrument(4);
            }
        });
        instrument5RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setApplyingInstrument(5);
            }
        });
        applyReverbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instrument instrument = mainGUI.getInstrument(instrumentNum - 1);

                if(instrument != null){
                    instrument.addReverb(reverbType, reverbStrength, reverbLength);
                    JOptionPane.showMessageDialog(reverbPanel, "Reverb has been applied to Instrument " + instrumentNum
                    + ". Type : " + reverbType
                    + ". Strength : " + reverbStrength
                    + ". Length : " + reverbLength + ".");
                }
                else{
                    JOptionPane.showMessageDialog(reverbPanel, "No instrument selected.");
                }
            }
        });


        instrument1RemoveReverb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(1);
            }
        });
        instrument2RemoveReverb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(2);
            }
        });
        instrument3RemoveReverb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(3);
            }
        });
        instrument4RemoveReverb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(4);
            }
        });
        instrument4RemoveReverb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(1);
            }
        });

        reverbRemovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instrument instrument = mainGUI.getInstrument(removingInstrumentNum - 1);
                if(instrument != null){
                    instrument.removeReverb();
                    JOptionPane.showMessageDialog(reverbPanel, "Reverb has been removed from Instrument " + removingInstrumentNum);
                }
            }
        });
    }


    public void setReverbStrength(int reverbStrength) {
        this.reverbStrength = reverbStrength;
    }
    public void setReverbType(String reverbType) {
        this.reverbType = reverbType;
    }
    public void setReverbLength(int reverbLength) {
        this.reverbLength = reverbLength;
    }
    public void setApplyingInstrument(int instrumentNum) {
        this.instrumentNum = instrumentNum;
    }
    public int getReverbStrength() {
        return this.reverbStrength;
    }
    public String getReverbType() {
        return this.reverbType;
    }
    public int getReverbLength() {
        return this.reverbLength;
    }
    public int getApplyingInstrument() {
        return this.instrumentNum;
    }


    public JPanel getRootPanel(){
        return this.reverbPanel;
    }

    public void setRemovingInstrumentNum(int removingInstrumentNum) {
        this.removingInstrumentNum = removingInstrumentNum;
    }
}
