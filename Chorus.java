import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chorus {
    private JPanel mainChorusPanel;
    private JPanel applyChorusPanel;
    private JPanel chorusInstrumentPanel;
    private JPanel toolsEffectsPanel;
    private JPanel chorusEffectPanel;
    private JComboBox toolsComboBox;
    private JComboBox effectsComboBox;
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

    private int modStrength;
    private int modDifference;

    private int instrumentNum;

    private MainGUI mainGUI;

    public Chorus(int modDifference, int modStrength){
        this.modDifference = modDifference;
        this.modStrength = modStrength;

        modStrengthSlider.setMinimum(0);
        modStrengthSlider.setMaximum(100);
        modStrengthSlider.setValue(50);

        modDifferenceSlider.setMinimum(0);
        modDifferenceSlider.setMaximum(2);
        modDifferenceSlider.setValue(1);



        modStrengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setModStrength(modStrengthSlider.getValue());
                modStrengthLabel.setText("Modulation Strength (" + modStrengthSlider.getValue() + "%) : ");
            }
        });
        modDifferenceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setModDifference(modDifferenceSlider.getValue());
                modDifferenceLabel.setText("Modulation Difference (" + modDifferenceSlider.getValue() + " semitones) : ");
            }
        });
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
        applyChorusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instrument instrument = mainGUI.getInstrument(instrumentNum - 1);

                if(instrument != null){
                    instrument.addChorus(modStrength, modDifference);
                    JOptionPane.showMessageDialog(mainChorusPanel, "Chorus has been applied to Instrument " + instrumentNum
                            + ". Strength : " + modStrength
                            + ". Semitone difference : " + modDifference + ".");
                }
                else{
                    JOptionPane.showMessageDialog(mainChorusPanel, "No instrument selected.");
                }
            }
        });
    }




    public void setModStrength(int modStrength) {
        this.modStrength = modStrength;
    }
    public void setModDifference(int modDifference) {
        this.modDifference = modDifference;
    }
    public void setInstrument(int instrumentNum) {
        this.instrumentNum = instrumentNum;
    }
    public int getModStrength() {
        return this.modStrength;
    }
    public int getModDifference() {
        return this.modDifference;
    }
    public int getApplyingInstrumentNum() {
        return this.instrumentNum;
    }
    public JPanel getRootPanel(){
        return mainChorusPanel;
    }

}
