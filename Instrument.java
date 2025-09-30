import javax.sound.midi.*;
import java.util.Arrays;

public class Instrument {
        private int[][] timeline = new int[128][2056];
        private int type;

        private addInstrumentForm addInstrumentForm;

        public Instrument(int type) {
                for(int i = 0; i < 128; i++) {
                        for(int j = 0; j < 2056; j++) {
                                this.timeline[i][j] = 0;
                        }
                }
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

        public void play(int tempo) {
                try {
                        Synthesizer synth = MidiSystem.getSynthesizer();
                        synth.open();

                        MidiChannel[] channels = synth.getChannels();
                        MidiChannel channel = channels[0];


                        javax.sound.midi.Instrument[] instruments;
                        instruments = synth.getDefaultSoundbank().getInstruments();

                    if(this.type >= 0 && this.type < instruments.length) {
                                synth.loadInstrument(instruments[this.type]);
                                channel.programChange(this.type);
                        }


                    for(int i = 0; i < 2056; i++) {
                            for(int j = 0; j < 128; j++) {
                                    if(this.timeline[j][i] == 1) {
                                            channel.noteOn(j, 100);
                                    }
                            }
                            Thread.sleep(tempo);

                            for(int j = 0; j < 128; j++) {
                                    if(this.timeline[j][i] == 0) {
                                            channel.noteOff(j);
                                    }
                            }
                    }
                    synth.close();
                }
                catch(Exception exception){
                        exception.printStackTrace();
                }
        }
}
