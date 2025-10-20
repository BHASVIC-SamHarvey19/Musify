import javax.sound.midi.*;
import javax.swing.*;
import java.util.Arrays;

public class Instrument {
        private int[][] timeline = new int[128][1024];
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


        public Instrument(int type) {
                this.timeline = new int[128][1024];
                this.type = type;

        }

        public void addNote(int note, int time) {
                this.timeline[note][time] = 1;
        }

        public void removeNote(int note, int time) {
                this.timeline[note][time] = 0;
        }

        public int getType() {
                return this.type;
        }

        public void setType(int type) {
                this.type = type;
        }

        public int getNoteState(int note, int time) {
                return this.timeline[note][time];
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

                        for(playTime = playTime; playTime < 1024 && isPlaying(); playTime++) {
                                if(isPaused()){
                                        playTime--;
                                        Thread.sleep(50);
                                        continue;
                                }
                                for(int i = 0; i<128; i++) {
                                        if(timeline[i][playTime] == 1) {
                                                channel.noteOn(i, 100);
                                        }
                                        else{
                                                channel.noteOff(i);
                                        }
                                }
                                progressBar.setValue(playTime);
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
        public int[][] getTimeline() {
                return timeline;
        }


        public void addReverb(String reverbType, int reverbStrength, int reverbLength) {
                this.reverbAdded = true;
                this.reverbLength = reverbLength;
                this.reverbStrength = reverbStrength;
                this.reverbType = reverbType;
        }
}
