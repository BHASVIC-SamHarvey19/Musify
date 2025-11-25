import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reverb {

    //declaring all components of the UI
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


    //declaring attributes for reverb related attributes
    private String reverbType;
    private int reverbLength;
    private int reverbStrength;

    //declaring attributes for the adding/removing reverb instrument number
    private int instrumentNum;

    private int removingInstrumentNum;

    private MainGUI mainGUI;

    public Reverb(MainGUI mainGUI){

        this.mainGUI = mainGUI;

        //setting maximum,minimum and current values for the reverb length and strength sliders
        reverbLengthSlider.setMaximum(5);
        reverbLengthSlider.setMinimum(0);
        reverbStrengthSlider.setMaximum(100);
        reverbStrengthSlider.setMinimum(0);

        reverbLengthSlider.setValue(reverbLength);
        reverbStrengthSlider.setValue(reverbStrength);

        //initialising attributes as minimum possible
        reverbLength = 0;
        reverbStrength = 0;
        reverbType = "";



        //listener for reverb length slider to change the displaying length and change the attribute
        reverbLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setReverbLength(reverbLengthSlider.getValue());
                reverbLengthLabel.setText("Reverb Length (" + reverbLengthSlider.getValue() + " seconds)");
            }
        });

        //listener for reverb strength slider to change the displaying strength and change the attribute
        reverbStrengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setReverbStrength(reverbStrengthSlider.getValue());
                reverbStrengthLabel.setText("Reverb Strength (" + reverbStrengthSlider.getValue() + "%)");
            }
        });

        //MULTIPLE LISTENERS TO SET THE TYPE OF REVERB TO BE ADDED
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

        //MULTIPLE LISTENERS TO SET THE INSTRUMENT THAT REVERB IS BEING APPLIED TO
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

        //listener for the applyReverbButton
        //adds reverb to the instrument through a method in the instrument class
        //displays a message to show that reverb has been added
        //if the instrument does not exist, an error message appears
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


        //MULTIPLE LISTENERS TO SET THE INSTRUMENT FOR REVERB TO BE REMOVED TO
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

        //listener for reverbRemovalButton
        //removes reverb if the instrument exists
        //displays a message to show that reverb has been removed
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

    //setter for reverbStrength attribute
    public void setReverbStrength(int reverbStrength) {
        this.reverbStrength = reverbStrength;
    }

    //setter for reverbType attribute
    public void setReverbType(String reverbType) {
        this.reverbType = reverbType;
    }

    //setter for reverbLengthAttribute
    public void setReverbLength(int reverbLength) {
        this.reverbLength = reverbLength;
    }

    //setter for instrumentNum attribute
    public void setApplyingInstrument(int instrumentNum) {
        this.instrumentNum = instrumentNum;
    }

    //getter for reverbStrength attribute
    public int getReverbStrength() {
        return this.reverbStrength;
    }

    //getter for reverbType attribute
    public String getReverbType() {
        return this.reverbType;
    }

    //getter for reverbLength attribute
    public int getReverbLength() {
        return this.reverbLength;
    }

    //getter for instrumentNum attribute
    public int getApplyingInstrument() {
        return this.instrumentNum;
    }

    //getter for root panel
    public JPanel getRootPanel(){
        return this.reverbPanel;
    }

    //setter for removingInstrumentNum attribute
    public void setRemovingInstrumentNum(int removingInstrumentNum) {
        this.removingInstrumentNum = removingInstrumentNum;
    }
}
