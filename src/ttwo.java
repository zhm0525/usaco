/*
ID: zhm05251
LANG: JAVA
TASK: ttwo
*/

import java.io.*;

public class ttwo {
    private static final String INPUT_FILE_NAME = "ttwo.in";
    private static final String OUTPUT_FILE_NAME = "ttwo.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        PositionState positionState = new PositionState();
        for (int i = 0; i < 10; i++) {
            String input = f.readLine();
            for (int j = 0, length = input.length(); j < length; j++) {
                if (input.charAt(j) == '*') {
                    map[i][j] = false;
                } else {
                    map[i][j] = true;
                    if (input.charAt(j) == 'C') {
                        positionState.setC(i, j);
                    } else if (input.charAt(j) == 'F') {
                        positionState.setF(i, j);
                    }
                }
            }
        }
        markPositionState(positionState);
        while (!positionState.isMeet()) {
            positionState = positionState.getNextPositionState();
            if (isPositionStateMarked(positionState)) {
                break;
            }
            markPositionState(positionState);
        }
        if (positionState.isMeet()) {
            out.println(String.valueOf(positionState.getStep()));
        } else {
            out.println("0");
        }
        out.close();
    }

    private static boolean[][][][][][] flags = new boolean[10][10][4][10][10][4];

    private static void markPositionState(PositionState positionState) {
        flags[positionState.getCX()][positionState.getCY()][positionState.getCD()]
                [positionState.getFX()][positionState.getFY()][positionState.getFD()] = true;
    }

    private static boolean isPositionStateMarked(PositionState positionState) {
        return flags[positionState.getCX()][positionState.getCY()][positionState.getCD()]
                [positionState.getFX()][positionState.getFY()][positionState.getFD()];
    }

    private static final int[][] DIRECTION = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static boolean[][] map = new boolean[10][10];

    private static class PositionState {
        int cx, cy, cd, fx, fy, fd;
        int step;

        public PositionState() {
            cd = 0;
            fd = 0;
            step = 0;
        }

        public PositionState(int cx, int cy, int cd, int fx, int fy, int fd, int step) {
            this.cx = cx;
            this.cy = cy;
            this.cd = cd;
            this.fx = fx;
            this.fy = fy;
            this.fd = fd;
            this.step = step;
        }

        public void setC(int x, int y) {
            cx = x;
            cy = y;
        }

        public void setF(int x, int y) {
            fx = x;
            fy = y;
        }

        public int getCX() {
            return cx;
        }

        public int getCY() {
            return cy;
        }

        public int getCD() {
            return cd;
        }

        public int getFX() {
            return fx;
        }

        public int getFY() {
            return fy;
        }

        public int getFD() {
            return fd;
        }

        public int getStep() {
            return step;
        }

        private boolean isMeet() {
            return cx == fx && cy == fy;
        }

        public PositionState getNextPositionState() {
            int cd1 = cd, cx1 = cx, cy1 = cy;
            if (isNextPositionAvailable(cx, cy, cd1)) {
                cx1 = cx + DIRECTION[cd1][0];
                cy1 = cy + DIRECTION[cd1][1];
            } else {
                cd1 = (cd1 + 1) % 4;
            }

            int fd1 = fd, fx1 = fx, fy1 = fy;
            if (isNextPositionAvailable(fx, fy, fd1)) {
                fx1 = fx + DIRECTION[fd1][0];
                fy1 = fy + DIRECTION[fd1][1];
            } else {
                fd1 = (fd1 + 1) % 4;
            }
            return new PositionState(cx1, cy1, cd1, fx1, fy1, fd1, step + 1);
        }

        private boolean isNextPositionAvailable(int x, int y, int d) {
            int x1 = x + DIRECTION[d][0], y1 = y + DIRECTION[d][1];
            return 0 <= x1 && x1 < 10 && 0 <= y1 && y1 < 10 && map[x1][y1];
        }
    }

}
