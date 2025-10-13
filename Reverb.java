import javax.swing.*;

public class Reverb {
    private JPanel reverbPanel;
    private JPanel toolsEffectsPanel;
    private JPanel applyReverbPanel;
    private JComboBox toolsComboBox;
    private JComboBox effectsComboBox;
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



    private String reverbType;
    private int reverbLength;
    private int reverbStrength;

    private Instrument instrument;

    public Reverb(String reverbType, int reverbLength, int reverbStrength){
        this.reverbType = reverbType;
        this.reverbLength = reverbLength;
        this.reverbStrength = reverbStrength;
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
    public void setApplyingInstrument(Instrument instrument) {
        this.instrument = instrument;
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
    public Instrument getApplyingInstrument() {
        return this.instrument;
    }

}
