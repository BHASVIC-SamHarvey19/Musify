import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

    private int instrumentNum;

    public Reverb(String reverbType, int reverbLength, int reverbStrength){
        this.reverbType = reverbType;
        this.reverbLength = reverbLength;
        this.reverbStrength = reverbStrength;

        reverbLengthSlider.setMaximum(5);
        reverbLengthSlider.setMinimum(0);
        reverbStrengthSlider.setMaximum(100);
        reverbStrengthSlider.setMinimum(0);

        reverbLengthSlider.setValue(reverbLength);
        reverbStrengthSlider.setValue(reverbStrength);




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
        return instrumentNum;
    }


    public JPanel getRootPanel(){
        return this.reverbPanel;
    }
}
