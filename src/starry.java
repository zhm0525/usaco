/*
ID: zhm05251
LANG: JAVA
TASK: starry
*/

import java.io.*;
import java.util.*;

public class starry {

    private static final String INPUT_FILE_NAME = "starry.in";
    private static final String OUTPUT_FILE_NAME = "starry.out";

    private static int m, n;
    private static int[][] map;
    private static int[][] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            String input = f.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        int t = 0;
        colors = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0 && colors[i][j] == 0) {
                    t++;
                    colors[i][j] = t;
                    color(i, j, t);
                }
            }
        }

        Rect[] rects = new Rect[t];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (colors[i][j] > 0) {
                    int color = colors[i][j];
                    Rect rect = rects[color - 1];
                    if (rect == null) {
                        rect = new Rect(color);
                        rects[color - 1] = rect;
                    }
                    rect.set(i, j);
                }
            }
        }

        int r = 0;
        for (int i = 0; i < t; i++) {
            Rect rectI = rects[i];
            if (rectI.isTargetColorSet()) {
                continue;
            }
            r++;
            rectI.setTargetColor(r);
            for (int j = i + 1; j < t; j++) {
                Rect rectJ = rects[j];
                if (rectJ.isTargetColorSet()) {
                    continue;
                }
                if (rectJ.isSimilar(rectI)) {
                    rectJ.setTargetColor(rectI.getTargetColor());
                }
            }
        }

        for (Rect rect : rects) {
            if (rect.isChangeColor()) {
                int[] xy = rect.getXY();
                for (int i = xy[0]; i <= xy[1]; i++) {
                    for (int j = xy[2]; j <= xy[3]; j++) {
                        if (colors[i][j] == rect.getColor()) {
                            colors[i][j] = rect.getTargetColor();
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                out.print(colors[i][j] == 0 ? '0' : (char) ('a' - 1 + colors[i][j]));
            }
            out.println();
        }

        out.close();
    }

    private static void color(int i, int j, int x) {
        for (int p = -1; p <= 1; p++) {
            if (i + p < 0 || i + p >= m) {
                continue;
            }
            for (int q = -1; q <= 1; q++) {
                if (j + q < 0 || j + q >= n) {
                    continue;
                }
                if (map[i + p][j + q] > 0 && colors[i + p][j + q] == 0) {
                    colors[i + p][j + q] = x;
                    color(i + p, j + q, x);
                }
            }
        }
    }

    private static class Rect {
        private int mColor;
        private int x1, x2, y1, y2;

        private int mTargetColor;

        public Rect(int color) {
            mColor = color;
            mTargetColor = 0;
            x1 = Integer.MAX_VALUE;
            x2 = -Integer.MAX_VALUE;
            y1 = Integer.MAX_VALUE;
            y2 = -Integer.MAX_VALUE;
        }

        public int getColor() {
            return mColor;
        }

        public boolean isTargetColorSet() {
            return mTargetColor > 0;
        }

        public void setTargetColor(int color) {
            mTargetColor = color;
        }

        public int getTargetColor() {
            return mTargetColor;
        }

        public boolean isChangeColor() {
            return mColor != mTargetColor;
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

        public boolean isSimilar(Rect rect) {
            int[] xy = rect.getXY();
            if (x2 - x1 == xy[1] - xy[0] && y2 - y1 == xy[3] - xy[2]) {
                if (checkSimilar1(rect) || checkSimilar2(rect) || checkSimilar3(rect) || checkSimilar4(rect)) {
                    return true;
                }
            }
            if (x2 - x1 == xy[3] - xy[2] && y2 - y1 == xy[1] - xy[0]) {
                if (checkSimilar5(rect) || checkSimilar6(rect) || checkSimilar7(rect) || checkSimilar8(rect)) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkSimilar1(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[0] + (i - x1);
                    int y = xy[2] + (j - y1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSimilar2(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[1] - (i - x1);
                    int y = xy[2] + (j - y1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSimilar3(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[0] + (i - x1);
                    int y = xy[3] - (j - y1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSimilar4(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[1] - (i - x1);
                    int y = xy[3] - (j - y1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSimilar5(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[0] + (j - y1);
                    int y = xy[2] + (i - x1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSimilar6(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[0] + (j - y1);
                    int y = xy[3] - (i - x1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSimilar7(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[1] - (j - y1);
                    int y = xy[2] + (i - x1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean checkSimilar8(Rect rect) {
            int[] xy = rect.getXY();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    int x = xy[1] - (j - y1);
                    int y = xy[3] - (i - x1);
                    if (colors[i][j] == mColor &&
                            colors[x][y] != rect.getColor()) {
                        return false;
                    }
                    if (colors[i][j] != mColor &&
                            colors[x][y] == rect.getColor()) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
