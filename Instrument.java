import javax.sound.midi.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Instrument {
        private List<Note> notes = new ArrayList<>();
        private int type;

        private boolean playing = false;
        private boolean paused = false;
        private int playTime = 0;
        private Thread playThread;

        private JProgressBar progressBar;

        private boolean reverbAdded = false;
        private String reverbType;
        private int reverbStrength;
        private int reverbLength;

        private int modDifference;
        private int modStrength;
        private boolean chorusAdded = false;


        public Instrument(int type) {
                this.type = type;

        }

        public void addNote(Note note) {
                this.notes.add(note);
        }

        public void removeNote(Note note) {
                this.notes.remove(note);
        }

        public int getType() {
                return this.type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public List<Note> getNotes() {
                return this.notes;
        }

        public synchronized void play(int tempo) {
                if(playing) {
                        return;
                }
                playing = true;
                paused = false;
                playThread = new Thread(() -> {try {
                        Synthesizer synth = MidiSystem.getSynthesizer();
                        synth.open();

                        MidiChannel[] channels = synth.getChannels();
                        MidiChannel channel = channels[0];


                        javax.sound.midi.Instrument[] instruments;
                        instruments = synth.getDefaultSoundbank().getInstruments();

                        synth.loadInstrument(instruments[this.type]);
                        channel.programChange(this.type);

                        for(int time = playTime; time < 256 && isPlaying(); time++) {
                                if(isPaused()){
                                        Thread.sleep(50);
                                        time--;
                                        continue;
                                }
                                playTime = time;
                                for(int i = 0; i < notes.size(); i++) {
                                        Note note = notes.get(i);

                                        if(note.getStart() == time) {
                                                if(reverbAdded && reverbType != null){
                                                        playNoteWithReverb(channel, note.getPitch(), tempo);
                                                }
                                                else if(chorusAdded){
                                                        playNoteWithChorus(channel, note.getPitch(), tempo);
                                                }
                                                else{
                                                        channel.noteOn(note.getPitch(), 100);
                                                        new Thread(() -> {
                                                                try {
                                                                        Thread.sleep(note.getLength() * tempo);
                                                                        channel.noteOff(note.getPitch());
                                                                }
                                                                catch(InterruptedException ignored){}

                                                        }).start();
                                                }
                                        }
                                        if(note.getStart() + note.getLength() == playTime) {
                                                channel.noteOff(note.getPitch());
                                        }
                                }
                                if(progressBar != null) {
                                progressBar.setValue(playTime);
                                }
                                Thread.sleep(tempo);
                        }


                        synth.close();
                        stopAndRewind();
                }
                catch(Exception exception){
                        exception.printStackTrace();
                }});
                playThread.start();
        }

        public boolean isPlaying() {
                return playing;
        }
        public boolean isPaused() {
                return paused;
        }
        public void stopAndRewind() {
                playing = false;
                paused = false;
                playTime = 0;
        }
        public synchronized void pause() {
                paused = true;
        }
        public synchronized void resume() {
                paused = false;
        }
        public void setProgressBar(JProgressBar progressBar){
                this.progressBar = progressBar;
        }



        public void addReverb(String reverbType, int reverbStrength, int reverbLength) {
                this.reverbAdded = true;
                this.reverbLength = reverbLength;
                this.reverbStrength = reverbStrength;
                this.reverbType = reverbType;
        }

        public void playNoteWithReverb(MidiChannel channel, int note, int time) {
                int baseDelay = 100;
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

                int delay = baseDelay * reverbLength;
                int echoAmount = reverbStrength / 20;

                if(echoAmount < 1){
                        echoAmount = 1;
                }
                for(int i = 0; i < echoAmount; i++) {
                        int echoVol = i * (reverbStrength / echoAmount);
                        echoVol = 100 - echoVol;
                        if(echoVol < 20){
                                echoVol = 20;
                        }

                        int echoDelay = delay * i;
                        echoDelay = echoDelay / echoAmount;

                        final int finalEchoVol = echoVol;
                        final int finalEchoDelay = echoDelay;

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

        public void addChorus(int modStrength, int modDifference){
                this.modStrength = modStrength;
                this.modDifference = modDifference;
                this.chorusAdded = true;
        }

        public void removeReverb(){
                this.reverbAdded = false;
        }

        public void removeChorus(){
                this.chorusAdded = false;
        }

        public void playNoteWithChorus(MidiChannel channel, int note, int time) {
                int instrumentAmount = 1 + (modStrength / 40);
                int vol = 100;

                for(int i = -instrumentAmount; i <= instrumentAmount; i++) {
                        if(i == 0){
                                continue;
                        }
                        int modulatedNote =  note + (i * modDifference);
                        if(modulatedNote < 0 || modulatedNote > 127){
                                continue;
                        }
                        int instrumentVol;
                        if(i < 0){
                                instrumentVol = vol - (i * -15);
                        }
                        else{
                                instrumentVol = vol - (i * 15);
                        }
                        if(instrumentVol < 20){
                                instrumentVol = 20;
                        }

                        final int finalInstrumentVol = instrumentVol;
                        final int finalNote = modulatedNote;

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
