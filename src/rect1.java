/*
ID: zhm05251
LANG: JAVA
TASK: rect1
*/

import java.io.*;
import java.util.*;

public class rect1 {

    private static final String INPUT_FILE_NAME = "rect1.in";
    private static final String OUTPUT_FILE_NAME = "rect1.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Rect> list = new ArrayList<>();
        list.add(new Rect(0, 0, m, n, 1));
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Rect rect = new Rect(x1, y1, x2, y2, c);
            for (int j = 0, size = list.size(); j < size; j++) {
                list.addAll(list.get(j).divideBy(rect));
            }
            list.add(rect);
        }

        int[] colors = new int[2500 + 1];
        for (Rect rect : list) {
            colors[rect.getColor()] += rect.getArea();
        }

        for (int i = 1; i <= 2500; i++) {
            if (colors[i] > 0) {
                out.println("" + i + " " + colors[i]);
            }
        }
        out.close();
    }

    private static class Rect {

        private int x1, y1, x2, y2;
        private int color;

        public Rect(int x1, int y1, int x2, int y2, int c) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = c;
        }

        public int getColor() {
            return color;
        }

        public int getArea() {
            return (x2 - x1) * (y2 - y1);
        }

        public int[] getXY() {
            return new int[]{x1, y1, x2, y2};
        }

        public void setInvisible() {
            this.x1 = 0;
            this.y1 = 0;
            this.x2 = 0;
            this.y2 = 0;
        }

        public List<Rect> divideBy(Rect rect) {
            List<Rect> list = new ArrayList<>();

            int[] xy = rect.getXY();
            int rx1 = xy[0];
            int ry1 = xy[1];
            int rx2 = xy[2];
            int ry2 = xy[3];

            if (rx2 <= x1 || x2 <= rx1 ||
                    ry2 <= y1 || y2 <= ry1) {
                return list;
            }

            if (rx1 <= x1) {
                if (rx2 < x2) {
                    list.add(new Rect(rx2, y1, x2, y2, color));
                    x2 = rx2;
                }
            } else {
                if (rx2 < x2) {
                    list.add(new Rect(x1, y1, rx1, y2, color));
                    list.add(new Rect(rx2, y1, x2, y2, color));
                    x1 = rx1;
                    x2 = rx2;
                } else {
                    list.add(new Rect(x1, y1, rx1, y2, color));
                    x1 = rx1;
                }
            }

            if (ry1 <= y1) {
                if (ry2 < y2) {
                    list.add(new Rect(x1, ry2, x2, y2, color));
                }
            } else {
                if (ry2 < y2) {
                    list.add(new Rect(x1, y1, x2, ry1, color));
                    list.add(new Rect(x1, ry2, x2, y2, color));
                } else {
                    list.add(new Rect(x1, y1, x2, ry1, color));
                }
            }

            this.setInvisible();
            return list;
        }
    }
}