import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addInstrumentForm {
    private JPanel panel1;
    private JPanel addInstrumentPanel;
    private JButton addInstrumentButton;
    private JComboBox instrumentTypeComboBox;
    private JLabel addInstrumentLabel;
    private JLabel instrumentTypeLabel;



    private mainGUI mainGUI;

    private int chosenInstrument;

    public JPanel getAddInstrumentPanel(){
        return addInstrumentPanel;
    }

    public addInstrumentForm(mainGUI mainGUI) {
        this.mainGUI = mainGUI;
        instrumentTypeComboBox.addActionListener(e -> {
            if ("Piano".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 0;
            }else if("Electric Piano 1".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 4;
            }else if("Electric Piano 2".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 5;
            }else if("Vibraphone".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 11;
            }else if("Reed Organ".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 20;
            }else if("Nylon String Guitar".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 24;
            }else if("Steel String Guitar".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 25;
            }else if("Jazz Guitar".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 26;
            }else if("Clean Guitar".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 27;
            }else if("Acoustic Bass".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 32;
            }else if("Slap Bass".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 36;
            }else if("Synth Bass".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 38;
            }else if("Violin".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 40;
            }else if("Viola".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 41;
            }else if("Cello".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 42;
            }else if("Contrabass".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 43;
            }else if("Harp".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 46;
            }else if("Trumpet".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 56;
            }else if("Trombone".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 57;
            }else if("French Horns".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 60;
            }else if("Alto Sax".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 65;
            }else if("Clarinet".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 71;
            }else if("Flute".equals(instrumentTypeComboBox.getSelectedItem())) {
                chosenInstrument = 73;
            }
            mainGUI.setChosenInstrumentNum(chosenInstrument);
        });
        addInstrumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.setInstrumentButton((String) instrumentTypeComboBox.getSelectedItem());


                Window window = SwingUtilities.getWindowAncestor(addInstrumentPanel);
                window.dispose();
            }
        });


    }
    public String getChosenInstrument(){
        return instrumentTypeComboBox.getSelectedItem().toString();
    }

    public int getChosenInstrumentNum(){
        return chosenInstrument;
    }

}
