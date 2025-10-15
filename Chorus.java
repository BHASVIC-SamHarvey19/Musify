import javax.swing.*;

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

    private Instrument instrument;

    public Chorus(int modDifference, int modStrength){
        this.modDifference = modDifference;
        this.modStrength = modStrength;
    }


    public void setModStrength(int modStrength) {
        this.modStrength = modStrength;
    }
    public void setModDifference(int modDifference) {
        this.modDifference = modDifference;
    }
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    public int getModStrength() {
        return this.modStrength;
    }
    public int getModDifference() {
        return this.modDifference;
    }
    public Instrument getApplyingInstrument() {
        return this.instrument;
    }
    public JPanel getRootPanel(){
        return mainChorusPanel;
    }

}
