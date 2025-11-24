import javax.sound.midi.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Instrument {

        //attributes declaration for note related items
        private List<Note> notes = new ArrayList<>();
        private int type;

        //attributes declaration for play method related items
        private boolean playing = false;
        private boolean paused = false;
        private int playTime = 0;
        private Thread playThread;

        private JProgressBar progressBar;

        //attributes declaration for effects related items
        private boolean reverbAdded = false;
        private String reverbType;
        private int reverbStrength;
        private int reverbLength;
        private int modDifference;
        private int modStrength;
        private boolean chorusAdded = false;

        //constructor for instrument class - only declares type attribute
        public Instrument(int type) {
                this.type = type;

        }

        //method for adding a note
        public void addNote(Note note) {
                this.notes.add(note);
        }

        //method for removing a note
        public void removeNote(Note note) {
                this.notes.remove(note);
        }

        //getter for type attribute
        public int getType() {
                return this.type;
        }

        //setter for type attribute
        public void setType(int type) {
                this.type = type;
        }

        //getter for the list of notes (previous timeline)
        public List<Note> getNotes() {
                return this.notes;
        }

        //method for playing the sequence
        public synchronized void play(int tempo) {

                //checks if it is already playing, and if it is, stop the play method
                if(playing) {
                        return;
                }

                //sets playing attributes to true
                playing = true;
                paused = false;

                //opens a thread to play the sequence and starts this thread
                playThread = new Thread(() -> {try {

                        //get a synthesizer and open in from the midi system
                        Synthesizer synth = MidiSystem.getSynthesizer();
                        synth.open();

                        //get a playing channel and set a MidiChannel type attribute to this channel
                        MidiChannel[] channels = synth.getChannels();
                        MidiChannel channel = channels[0];

                        //get all available instruments in the default sound bank and set the instrument to the desired instrument
                        javax.sound.midi.Instrument[] instruments;
                        instruments = synth.getDefaultSoundbank().getInstruments();


                        synth.loadInstrument(instruments[this.type]);
                        channel.programChange(this.type);

                        //for loop to cycle through each note in the sequence
                        for(int time = playTime; time < 256 && isPlaying(); time++) {

                                //check if the sequence is paused, and stop playing if it is
                                if(isPaused()){
                                        Thread.sleep(50);
                                        time--;
                                        continue;
                                }
                                playTime = time;

                                //check through each note
                                for(int i = 0; i < notes.size(); i++) {

                                        //set a Note type object to the current note selected
                                        Note note = notes.get(i);

                                        if(note.getStart() == time) {

                                                //playing notes with effects if they have been added
                                                if(reverbAdded && reverbType != null){
                                                        playNoteWithReverb(channel, note.getPitch(), tempo);
                                                }
                                                else if(chorusAdded){
                                                        playNoteWithChorus(channel, note.getPitch(), tempo);
                                                }

                                                //play note regularly if no effect has been added
                                                else{
                                                        channel.noteOn(note.getPitch(), 100);

                                                        //sleep the thread for the delay time, so that the notes length is correct
                                                        new Thread(() -> {
                                                                try {
                                                                        Thread.sleep(note.getLength() * tempo);
                                                                        channel.noteOff(note.getPitch());
                                                                }
                                                                catch(InterruptedException ignored){}

                                                        }).start();
                                                }
                                        }

                                        //stop note after the notes length has ended
                                        if(note.getStart() + note.getLength() == playTime) {
                                                channel.noteOff(note.getPitch());
                                        }
                                }

                                //set the value for the progress bar
                                if(progressBar != null) {
                                progressBar.setValue(playTime);
                                }
                                Thread.sleep(tempo);
                        }

                        //close the synthesizer and stop the sequence playing after it has finished
                        synth.close();
                        stopAndRewind();
                }
                catch(Exception exception){
                        exception.printStackTrace();
                }});

                //start the playThread
                playThread.start();
        }

        //getter for playing attribute
        public boolean isPlaying() {
                return playing;
        }

        //getter for paused attribute
        public boolean isPaused() {
                return paused;
        }

        //method to stop the music and rewind the sequence
        public void stopAndRewind() {
                playing = false;
                paused = false;
                playTime = 0;
        }

        //method for pausing the sequence
        public synchronized void pause() {
                paused = true;
        }

        //method for resuming the sequence
        public synchronized void resume() {
                paused = false;
        }

        //sets the value of the progress bar
        public void setProgressBar(JProgressBar progressBar){
                this.progressBar = progressBar;
        }


        //adds reverb to the instrument with the necessary attributes
        public void addReverb(String reverbType, int reverbStrength, int reverbLength) {
                this.reverbAdded = true;
                this.reverbLength = reverbLength;
                this.reverbStrength = reverbStrength;
                this.reverbType = reverbType;
        }

        //method for playing a note with the reverb effect
        public void playNoteWithReverb(MidiChannel channel, int note, int time) {

                //declare the delay before it has changed due to other reverb related attributes
                int baseDelay = 100;

                //changes the base delay due to reverb type
                switch(reverbType) {
                        case "Digital":
                                baseDelay = 80;
                                break;
                        case "Hall":
                                baseDelay = 200;
                                break;
                        case "Room":
                                baseDelay = 100;
                                break;
                        case "Chamber":
                                baseDelay = 150;
                                break;
                        case "Shimmer":
                                baseDelay = 250;
                                break;
                        default:
                                break;
                }

                //uses the length and strength of reverb to alter how the effect sounds
                int delay = baseDelay * reverbLength;
                int echoAmount = reverbStrength / 20;

                //sets the minimum amount of echoes to 1
                if(echoAmount < 1){
                        echoAmount = 1;
                }

                //for loop for playing all the echoes in
                for(int i = 0; i < echoAmount; i++) {

                        //sets the value of the volume of each echo
                        //decreases as echoes go on
                        //minimum value of 20
                        int echoVol = i * (reverbStrength / echoAmount);
                        echoVol = 100 - echoVol;
                        if(echoVol < 20){
                                echoVol = 20;
                        }

                        //sets the amount of delay between reverb echoes
                        int echoDelay = delay * i;
                        echoDelay = echoDelay / echoAmount;


                        //sets the attributes as final so that the thread works properly
                        final int finalEchoVol = echoVol;
                        final int finalEchoDelay = echoDelay;

                        //thread for playing each echo
                        new Thread(() -> {
                                try{
                                        Thread.sleep(finalEchoDelay);
                                        channel.noteOn(note, finalEchoVol);
                                        Thread.sleep(time / 2);
                                        channel.noteOff(note);
                                }
                                catch(InterruptedException ignored){

                                }
                        }).start();
                }
        }

        //method for adding chorus effect with relevant attributes
        public void addChorus(int modStrength, int modDifference){
                this.modStrength = modStrength;
                this.modDifference = modDifference;
                this.chorusAdded = true;
        }

        //method for removing reverb from an instrument
        public void removeReverb(){
                this.reverbAdded = false;
        }

        //method for removing chorus from an instrument
        public void removeChorus(){
                this.chorusAdded = false;
        }


        //method for playing a note with the chorus effect
        public void playNoteWithChorus(MidiChannel channel, int note, int time) {

                //setst the amount of instruments based on the strength of modulation
                int instrumentAmount = 1 + (modStrength / 40);
                int vol = 100;

                //cycles between negative instruments (lower notes) and positive instruments (higher notes)
                for(int i = -instrumentAmount; i <= instrumentAmount; i++) {

                        //ignore the original sound
                        if(i == 0){
                                continue;
                        }

                        //sets the pitch of the modulated note
                        int modulatedNote =  note + (i * modDifference);
                        if(modulatedNote < 0 || modulatedNote > 127){
                                continue;
                        }
                        int instrumentVol;

                        //different volume setting for positive and negative instrument
                        //means that volume is never negative (not possible)
                        if(i < 0){
                                instrumentVol = vol - (i * -15);
                        }
                        else{
                                instrumentVol = vol - (i * 15);
                        }

                        //sets base instrument volume to 20
                        if(instrumentVol < 20){
                                instrumentVol = 20;
                        }

                        //sets the attributes as final so that they can be used in the thread
                        final int finalInstrumentVol = instrumentVol;
                        final int finalNote = modulatedNote;

                        //thread for playing each instruments modulated notes
                        new Thread(() -> {
                                try {
                                        channel.noteOn(finalNote, finalInstrumentVol);
                                        Thread.sleep(time / 2);
                                        channel.noteOff(finalNote);
                                }
                                catch (InterruptedException ignored){}

                        }).start();
                }
        }
}
