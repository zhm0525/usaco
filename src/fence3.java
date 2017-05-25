/*
ID: zhm05251
LANG: JAVA
TASK: fence3
*/

import java.io.*;
import java.util.*;

public class fence3 {

    private static final String INPUT_FILE_NAME = "fence3.in";
    private static final String OUTPUT_FILE_NAME = "fence3.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        int sumX = 0, sumY = 0;

        List<Line> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            list.add(new Line(x1, y1, x2, y2));

            minX = Math.min(minX, Math.min(x1, x2));
            maxX = Math.max(maxX, Math.max(x1, x2));
            minY = Math.min(minY, Math.min(y1, y2));
            maxY = Math.max(maxY, Math.max(y1, y2));

            sumX += x1 + x2;
            sumY += y1 + y2;
        }

//        int mx = (minX + maxX) / 2;
//        int my = (minY + maxY) / 2;
        int mx = sumX / 2 / n;
        int my = sumY / 2 / n;
        int dx = maxX - minX;
        int dy = maxY - minY;

        double deltaX = Math.max(Math.max(0.3d * dx, 1d), 3d);
        double deltaY = Math.max(Math.max(0.3d * dy, 1d), 3d);

        double s = Double.MAX_VALUE, x = 0d, y = 0d;

//        for (double xt = mx - deltaX; xt <= mx + deltaX; xt += 0.1d) {
//            for (double yt = my - deltaY; yt <= my + deltaY; yt += 0.1d) {
//                double t = 0d;
//                for (Line line : list) {
//                    t += line.getDistance(xt, yt);
//                    if (t >= s) {
//                        break;
//                    }
//                }
//                if (t < s) {
//                    s = t;
//                    x = xt;
//                    y = yt;
//                }
//            }
//        }

        x = mx;
        y = my;
        s = 0d;
        for (Line line : list) {
            s += line.getDistance(x, y);
        }

        final double[][] d = {{0d, 0.1d}, {0d, -0.1d}, {0.1d, 0d}, {-0.1d, 0d}};

        boolean find = false;
        while (!find) {
            int k = -1;
            for (int i = 0; i < 4; i++) {
                double t = 0d;
                for (Line line : list) {
                    t += line.getDistance(x + d[i][0], y + d[i][1]);
                }
                if (t < s) {
                    s = t;
                    k = i;
                }
            }
            if (k >= 0) {
                x += d[k][0];
                y += d[k][1];
            }
            find = k < 0;
        }
        out.println(String.format("%.1f", x) + " " + String.format("%.1f", y) + " " + String.format("%.1f", s));
        out.close();
    }


    private static class Line {

        private double x1, y1, x2, y2;
//        private int a, b, c;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;

            //(y2-y1)x-(x2-x1)y-x1y2+x2y1=0
//            a = y2 - y1;
//            b = -(x2 - x1);
//            c = -x1 * y2 + x2 * y1;
        }

        // copy from Internet
        public double getDistance(double x, double y) {
            double space = 0;
            double a, b, c;
            a = lineSpace(x1, y1, x2, y2);
            b = lineSpace(x1, y1, x, y);
            c = lineSpace(x2, y2, x, y);
            if (c <= 0.000001 || b <= 0.000001) {
                space = 0;
                return space;
            }
            if (a <= 0.000001) {
                space = b;
                return space;
            }
            if (c * c >= a * a + b * b) {
                space = b;
                return space;
            }
            if (b * b >= a * a + c * c) {
                space = c;
                return space;
            }
            double p = (a + b + c) / 2;
            double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            space = 2 * s / a;
            return space;
        }

        private double lineSpace(double x1, double y1, double x2, double y2) {
            return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        }
    }
}
