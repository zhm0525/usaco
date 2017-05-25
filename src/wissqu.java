/*
ID: zhm05251
LANG: JAVA
TASK: wissqu
*/

import java.io.*;
import java.util.*;

public class wissqu {

    private static final String INPUT_FILE_NAME = "wissqu.in";
    private static final String OUTPUT_FILE_NAME = "wissqu.out";

    private static int[][] map = new int[4][4];
    private static int[] a = new int[]{3, 3, 3, 4, 3};
    private static boolean[][] b = new boolean[4][4];

    private static int count = 0;

    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        for (int i = 0; i < 4; i++) {
            String input = f.readLine();
            for (int j = 0; j < 4; j++) {
                map[i][j] = input.charAt(j) - 'A';
            }
        }

        search(0);

        out.println(String.valueOf(count));
        out.close();
    }

    private static void search(int x) {
        if (x >= 16) {
            if (count == 0) {
                for (int i = 0; i < 16; i++) {
                    out.println(mPath[i].getContent());
                }
            }
            count++;
        } else if (x == 0) {
            // place D
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!b[i][j] && isAvailablePlace(3, i, j)) {
                        a[3]--;
                        b[i][j] = true;
                        int r = map[i][j];
                        map[i][j] = 3;
                        mPath[x] = new Pos(3, i, j);
                        search(x + 1);
                        mPath[x] = null;
                        map[i][j] = r;
                        b[i][j] = false;
                        a[3]++;
                    }
                }
            }
        } else {
            for (int k = 0; k < 5; k++) {
                if (a[k] > 0) {
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (!b[i][j] && isAvailablePlace(k, i, j)) {
                                a[k]--;
                                b[i][j] = true;
                                int r = map[i][j];
                                map[i][j] = k;
                                mPath[x] = new Pos(k, i, j);
                                search(x + 1);
                                mPath[x] = null;
                                map[i][j] = r;
                                b[i][j] = false;
                                a[k]++;
                            }
                        }
                    }
                }
            }

        }
    }

    private static boolean isAvailablePlace(int k, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            if (x + i < 0 || x + i > 3) {
                continue;
            }
            for (int j = -1; j <= 1; j++) {
                if (y + j < 0 || y + j > 3) {
                    continue;
                }
                if (map[x + i][y + j] == k) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Pos {
        public char c;
        public int x;
        public int y;

        public Pos(int k, int x, int y) {
            this.c = (char) ('A' + k);
            this.x = x + 1;
            this.y = y + 1;
        }

        public String getContent() {
            return "" + c + " " + x + " " + y;
        }
    }

    private static Pos[] mPath = new Pos[16];
}
