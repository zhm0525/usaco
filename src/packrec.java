/*
ID: zhm05251
LANG: JAVA
TASK: packrec
*/

import java.io.*;
import java.util.*;

public class packrec {

    private static final String INPUT_FILE_NAME = "packrec.in";
    private static final String OUTPUT_FILE_NAME = "packrec.out";

    private static List<Rect> mAnswerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        Rect[] rects = new Rect[4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            rects[i] = new Rect(x, y);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    for (int k = 0; k < 4; k++) {
                        if (i != k && j != k) {
                            for (int l = 0; l < 4; l++) {
                                if (i != l && j != l && k != l) {
                                    for (int r0 = 0; r0 <= 1; r0++) {
                                        for (int r1 = 0; r1 <= 1; r1++) {
                                            for (int r2 = 0; r2 <= 1; r2++) {
                                                for (int r3 = 0; r3 <= 1; r3++) {
                                                    int x0 = rects[i].get(r0), y0 = rects[i].get(1 - r0);
                                                    int x1 = rects[j].get(r1), y1 = rects[j].get(1 - r1);
                                                    int x2 = rects[k].get(r2), y2 = rects[k].get(1 - r2);
                                                    int x3 = rects[l].get(r3), y3 = rects[l].get(1 - r3);

                                                    checkAndSaveAnswer(x0 + x1 + x2 + x3, max(y0, max(y1, max(y2, y3))));
                                                    checkAndSaveAnswer(max(x0 + x1 + x2, x3), max(y0, max(y1, y2)) + y3);
                                                    checkAndSaveAnswer(max(x0 + x1 + x3, x2 + x3), max(max(y0, y1) + y2, y3));
                                                    checkAndSaveAnswer(max(x0, x1) + x2 + x3, max(y0 + y1, max(y2, y3)));

                                                    if (x3 >= x0 && x1 >= x2) {
                                                        if (y3 <= y2) {
                                                            checkAndSaveAnswer(max(x0 + x1, x2 + x3), max(y0 + y3, y1 + y2));
                                                        } else {
                                                            checkAndSaveAnswer(max(x0, x1 + x3), max(y0 + y3, y1 + y2));
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Collections.sort(mAnswerList);
        out.println(String.valueOf(mAnswerList.get(0).getArea()));
        for (Rect rect : mAnswerList) {
            out.println(String.valueOf(rect.getX()) + " " + String.valueOf(rect.getY()));
        }
        out.close();
    }

    private static int max(int x, int y) {
        return x > y ? x : y;
    }

    private static void checkAndSaveAnswer(int x, int y) {
        if (mAnswerList.isEmpty()) {
            mAnswerList.add(new Rect(x, y));
        } else {
            int area = mAnswerList.get(0).getArea();
            if (x * y < area) {
                mAnswerList.clear();
                mAnswerList.add(new Rect(x, y));
            } else if (x * y == area) {
                boolean flag = true;
                for (Rect rect : mAnswerList) {
                    if (rect.isExist(x, y)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    mAnswerList.add(new Rect(x, y));
                }
            }
        }
    }

    private static class Rect implements Comparable<Rect> {

        private int x;
        private int y;

        public Rect(int x, int y) {
            if (x < y) {
                this.x = x;
                this.y = y;
            } else {
                this.x = y;
                this.y = x;
            }
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int get(int i) {
            if (i == 0) {
                return x;
            } else if (i == 1) {
                return y;
            }
            return 0;
        }

        public int getArea() {
            return x * y;
        }

        public boolean isExist(int x, int y) {
            return this.x == x && this.y == y || this.x == y && this.y == x;
        }

        @Override
        public int compareTo(Rect rect) {
            if (x < rect.getX()) {
                return -1;
            } else if (x > rect.getX()) {
                return 1;
            }
            if (y < rect.getY()) {
                return -1;
            } else if (y > rect.getY()) {
                return 1;
            }
            return 0;
        }
    }
}
