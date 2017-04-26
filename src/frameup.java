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

    private static final int LETTER_COUNT = 26;

    private static boolean[] mLetterUsed = new boolean[LETTER_COUNT];
    private static List<Frame> mFrames = new ArrayList<>();
    private static char[] mTempResult;
    private static List<String> mResults;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            String input = f.readLine();
            // assert the length of map[i] is n;
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != '.') {
                    char letter = map[i][j];
                    mLetterUsed[letter - 'A'] = true;
                    Frame curFrame = null;
                    for (Frame frame : mFrames) {
                        if (frame.getLetter() == letter) {
                            curFrame = frame;
                            break;
                        }
                    }
                    if (curFrame == null) {
                        curFrame = new Frame(letter);
                        mFrames.add(curFrame);
                    }
                    curFrame.set(i, j);
                }
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
                frame.addCovered(map[i][y1]);
                frame.addCovered(map[i][y2]);
            }

            for (int i = y1; i <= y2; i++) {
                // (x1, i), (x2, i)
                frame.addCovered(map[x1][i]);
                frame.addCovered(map[x2][i]);
            }
        }

        mTempResult = new char[mFrames.size()];
        mResults = new ArrayList<>();
        search(0);

        Collections.sort(mResults);
        for (String result : mResults) {
            out.println(result);
        }
        out.close();
    }

    private static void search(int index) {
        if (index >= mFrames.size()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = mFrames.size() - 1; i >= 0; i--) {
                stringBuffer.append(mTempResult[i]);
            }
            mResults.add(stringBuffer.toString());
        } else {
            for (Frame frame : mFrames) {
                if (mLetterUsed[frame.getLetter() - 'A'] &&
                        !frame.isCovered(mLetterUsed)) {
                    char letter = frame.getLetter();
                    mTempResult[index] = letter;
                    mLetterUsed[letter - 'A'] = false;
                    search(index + 1);
                    mLetterUsed[letter - 'A'] = true;
                }
            }
        }
    }

    private static class Frame {
        private char mLetter;
        private int x1, x2, y1, y2; //x1<x2, y1<y2
        private boolean[] mCovered;

        public Frame(char letter) {
            mLetter = letter;
            x1 = Integer.MAX_VALUE;
            x2 = -Integer.MAX_VALUE;
            y1 = Integer.MAX_VALUE;
            y2 = -Integer.MAX_VALUE;
            mCovered = new boolean[LETTER_COUNT];
        }

        public char getLetter() {
            return mLetter;
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

        public void addCovered(char letter) {
            addCovered(letter - 'A');
        }

        public void addCovered(int letterIndex) {
            if (letterIndex < 0 || letterIndex >= LETTER_COUNT) {
                return;
            } else if (mLetter - 'A' == letterIndex) {
                return;
            }
            mCovered[letterIndex] = true;
        }

        public boolean isCovered(boolean[] availables) {
            for (int i = 0; i < LETTER_COUNT; i++) {
                if (mCovered[i] && availables[i]) {
                    return true;
                }
            }
            return false;
        }

    }

}
