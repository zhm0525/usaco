/*
ID: zhm05251
LANG: JAVA
TASK: fence3
*/

import java.io.*;
import java.util.*;

public class fence4 {

    private static final String INPUT_FILE_NAME = "fence3.in";
    private static final String OUTPUT_FILE_NAME = "fence3.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int ox = Integer.parseInt(st.nextToken());
        int oy = Integer.parseInt(st.nextToken());
        Point observerPoint = new Point(ox, oy);

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }




        out.close();
    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
