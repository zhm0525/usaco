/*
ID: zhm05251
LANG: JAVA
TASK: frameup
*/

import java.io.*;
import java.util.*;

public class frameup {

    private static final String INPUT_FILE_NAME = "frameup.in";
    private static final String OUTPUT_FILE_NAME = "frameup.out";

    private static List<Frame> mFrames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        String[] map = new String[m];
        for (int i = 0; i < m; i++) {
            map[i] = f.readLine();
            // assert the length of map[i] is n;
            for (int j = 0; j < n; j++) {
                if (map[i].charAt(j) == '.') {
                    continue;
                }
                int id = map[i].charAt(j);
                Frame curFrame = getFrame(id);
                if (curFrame == null) {
                    curFrame = new Frame(id);
                    mFrames.add(curFrame);
                }
                curFrame.set(i, j);
            }
        }

        for (Frame frame : mFrames) {
            int[] xy = frame.getXY();
            int x1 = xy[0];
            int x2 = xy[1];
            int y1 = xy[2];
            int y2 = xy[3];
            for (int i = x1; i <= x2; i++) {
                // (i, y1), (i, y2)
                cover(frame, map[i].charAt(y1));
                cover(frame, map[i].charAt(y2));
            }

            for (int i = y1; i <= y2; i++) {
                // (x1, i), (x2, i)
                cover(frame, map[x1].charAt(i));
                cover(frame, map[x2].charAt(i));
            }
        }

        Collections.sort(mFrames);

        List<Frame> mChosenFrames = new ArrayList<>();
        for (int i = 0, size = mFrames.size(); i < size; i++) {
            Frame choseFrame = null;
            for (Frame frame : mFrames) {
                if (mChosenFrames.contains(frame)) {
                    continue;
                }
                if (!frame.isCovered(mChosenFrames)) {
                    choseFrame = frame;
                    break;
                }
            }
            mChosenFrames.add(choseFrame);
        }

        for (int i = mChosenFrames.size() - 1; i >= 0; i--) {
            out.print((char) (mChosenFrames.get(i).getId()));
        }
        out.println();
        out.close();
    }

    private static Frame getFrame(int id) {
        for (Frame frame : mFrames) {
            if (frame.getId() == id) {
                return frame;
            }
        }
        return null;
    }

    private static void cover(Frame frame, char c) {
        if (c == '.') {
            return;
        }
        int id = (int) c;
        if (id != frame.getId()) {
            frame.addCovered(getFrame(id));
        }
    }

    private static class Frame implements Comparable<Frame> {
        private int id; // convert from the char
        private int x1, x2, y1, y2; //x1<x2, y1<y2

        private List<Frame> mCoveredFrames;

        public Frame(int id) {
            this.id = id;
            x1 = Integer.MAX_VALUE;
            x2 = -Integer.MAX_VALUE;
            y1 = Integer.MAX_VALUE;
            y2 = -Integer.MAX_VALUE;

            mCoveredFrames = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public void set(int x, int y) {
            if (x < x1) {
                x1 = x;
            }
            if (x > x2) {
                x2 = x;
            }
            if (y < y1) {
                y1 = y;
            }
            if (y > y2) {
                y2 = y;
            }
        }

        public int[] getXY() {
            return new int[]{x1, x2, y1, y2};
        }

        public void addCovered(Frame frame) {
            mCoveredFrames.add(frame);
        }

        public void removeCovered(Frame frame) {
            mCoveredFrames.remove(frame);
        }

        public boolean isCovered(List<Frame> excepts) {
            for (Frame coveredFrame : mCoveredFrames) {
                if (!excepts.contains(coveredFrame)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int compareTo(Frame frame) {
            if (id > frame.getId()) {
                return -1;
            } else if (id < frame.getId()) {
                return 1;
            }
            return 0;
        }
    }

}
