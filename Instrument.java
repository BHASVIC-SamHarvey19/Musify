public class Instrument {
        private int[][] timeline = new int[128][128];
        private int type;

        public Instrument(int type) {
                for(int i = 0; i < 128; i++) {
                        for(int j = 0; j < 128; j++) {
                                this.timeline[i][j] = 0;
                        }
                }
                this.type = type;
        }
}
