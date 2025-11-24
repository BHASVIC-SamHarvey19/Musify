import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chorus {

    //declaring the items on the GUI
    private JPanel mainChorusPanel;
    private JPanel applyChorusPanel;
    private JPanel chorusInstrumentPanel;
    private JPanel toolsEffectsPanel;
    private JPanel chorusEffectPanel;
    private JLabel chorusLabel;
    private JLabel chorusInstrumentLabel;
    private JRadioButton instrument1RadioButton;
    private JRadioButton instrument2RadioButton;
    private JRadioButton instrument3RadioButton;
    private JRadioButton instrument4RadioButton;
    private JRadioButton instrument5RadioButton;
    private JPanel modStrengthPanel;
    private JPanel modDifferencePanel;
    private JSlider modDifferenceSlider;
    private JLabel modDifferenceLabel;
    private JSlider modStrengthSlider;
    private JLabel modStrengthLabel;
    private JButton applyChorusButton;
    private JLabel chorusRemovalLabel;
    private JPanel chorusRemovalPanel;
    private JRadioButton instrument1ChorusRemove;
    private JRadioButton instrument2ChorusRemove;
    private JRadioButton instrument3ChorusRemove;
    private JRadioButton instrument4ChorusRemove;
    private JRadioButton instrument5ChorusRemove;
    private JButton chorusRemovalButton;


    //attributes for the effects strength and difference in modulation
    private int modStrength;
    private int modDifference;

    //number attribute for the instrument to have its effect added
    private int instrumentNum;

    private MainGUI mainGUI;

    //number attribute for the instrument to have its effect removed
    private int removingInstrumentNum;

    //constructor for Chorus class
    public Chorus(MainGUI mainGUI){

        //setting minimum, maximum and starting values for the sliders for chorus attributes
        modStrengthSlider.setMinimum(0);
        modStrengthSlider.setMaximum(100);
        modStrengthSlider.setValue(50);

        modDifferenceSlider.setMinimum(0);
        modDifferenceSlider.setMaximum(2);
        modDifferenceSlider.setValue(1);



        //listener to set the value of modStrength based on the slider
        modStrengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setModStrength(modStrengthSlider.getValue());
                modStrengthLabel.setText("Modulation Strength (" + modStrengthSlider.getValue() + "%) : ");
            }
        });

        //listener to set the value for modDifference based on the slider
        modDifferenceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setModDifference(modDifferenceSlider.getValue());
                modDifferenceLabel.setText("Modulation Difference (" + modDifferenceSlider.getValue() + " semitones) : ");
            }
        });

        //A LOT of listeners which set the instrument that an effect will be added to
        instrument1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(1);
            }
        });
        instrument2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(2);
            }
        });
        instrument3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(3);
            }
        });
        instrument4RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(4);
            }
        });
        instrument5RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(5);
            }
        });

        //action listener for adding the chorus effect to an instrument
        applyChorusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instrument instrument = mainGUI.getInstrument(instrumentNum - 1);

                //if the instrument is present in the sequencer, add chorus and display that chorus has been added
                if(instrument != null){
                    instrument.addChorus(modStrength, modDifference);
                    JOptionPane.showMessageDialog(mainChorusPanel, "Chorus has been applied to Instrument " + instrumentNum
                            + ". Strength : " + modStrength
                            + ". Semitone difference : " + modDifference + ".");
                }

                //if the instrument is NOT present in the sequencer, display that this process cannot be done
                else{
                    JOptionPane.showMessageDialog(mainChorusPanel, "No instrument selected.");
                }
            }
        });

        //A LOT of listeners so that the user can choose what instrument they would like to remove the chorus effect from
        instrument1ChorusRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(1);
            }
        });
        instrument2ChorusRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(2);
            }
        });
        instrument3ChorusRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(3);
            }
        });
        instrument4ChorusRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(4);
            }
        });
        instrument4ChorusRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRemovingInstrumentNum(1);
            }
        });

        //action listener for the removing of chorus
        chorusRemovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instrument instrument = mainGUI.getInstrument(removingInstrumentNum - 1);

                //if the instrument is present in the sequencer, then the effect is removed and this process is displayed
                if(instrument != null){
                    instrument.removeChorus();
                    JOptionPane.showMessageDialog(mainChorusPanel, "Chorus has been removed from Instrument " + removingInstrumentNum);
                }
            }
        });
    }



    //setter for modStrength attribute
    public void setModStrength(int modStrength) {
        this.modStrength = modStrength;
    }

    //setter for modDifference attribute
    public void setModDifference(int modDifference) {
        this.modDifference = modDifference;
    }

    //setter for the instrument number for chorus to be added to
    public void setInstrument(int instrumentNum) {
        this.instrumentNum = instrumentNum;
    }

    //getter for modStrength attribute
    public int getModStrength() {
        return this.modStrength;
    }

    //getter for modDifference attribute
    public int getModDifference() {
        return this.modDifference;
    }

    //getter for the instrument for chorus to be applied to
    public int getApplyingInstrumentNum() {
        return this.instrumentNum;
    }

    //getter for the root panel of the UI
    public JPanel getRootPanel(){
        return mainChorusPanel;
    }

    //getter for the instrument for chorus to be removed from
    public void setRemovingInstrumentNum(int removingInstrumentNum) {
        this.removingInstrumentNum = removingInstrumentNum;
    }

}
