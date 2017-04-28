/*
ID: zhm05251
LANG: JAVA
TASK: fc
*/

import java.io.*;
import java.util.*;

public class fc {

    private static final String INPUT_FILE_NAME = "fc.in";
    private static final String OUTPUT_FILE_NAME = "fc.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            list.add(new Point(x, y));
        }

        // get p0 with the minimum y and leftmost x
        int k = 0;
        for (int i = 1; i < n; i++) {
            Point pointK = list.get(k);
            Point pointI = list.get(i);
            double xK = pointK.getX();
            double yK = pointK.getY();
            double xI = pointI.getX();
            double yI = pointI.getY();
            if ((yI < yK) || (yI == yK && xI < xK)) {
                k = i;
            }
        }
        Point point0 = list.get(k);
        list.remove(k);

        Point.setPoint0(point0);
        Collections.sort(list);

        Stack<Point> stack = new Stack<>();
        stack.push(point0);
        stack.push(list.get(0));
        for (int i = 1; i < n - 1; i++) {
            Point point = list.get(i);
            while (stack.size() >= 2 &&
                    Point.cross(stack.get(stack.size() - 2),
                            stack.get(stack.size() - 1),
                            point) <= 0) {
                stack.pop();
            }
            stack.push(point);
        }

        double s = stack.get(0).getDistance(stack.get(stack.size() - 1));
        for (int i = 0, size = stack.size(); i < size - 1; i++) {
            s += stack.get(i).getDistance(stack.get(i + 1));
        }

        out.println(String.format("%.2f", s));
        out.close();
    }

    private static class Point implements Comparable<Point> {

        private static Point mPoint0;

        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getDistance(Point point) {
            return Math.sqrt(Math.pow((x - point.getX()), 2) + Math.pow(y - point.getY(), 2));
        }

        // o is the common point. if cross < 0: p1 is left; cross > 0: p1 is right
        public static double cross(Point o, Point p1, Point p2) {
            return (p1.getX() - o.getX()) * (p2.getY() - o.getY()) - (p2.getX() - o.getX()) * (p1.getY() - o.getY());
        }

        public static void setPoint0(Point point) {
            mPoint0 = point;
        }

        @Override
        public int compareTo(Point point) {
            double c = cross(mPoint0, this, point);
            if (c > 0) {
                return -1;
            } else if (c < 0) {
                return 1;
            }
            double d1 = mPoint0.getDistance(this);
            double d2 = mPoint0.getDistance(point);
            if (d1 < d2) {
                return -1;
            } else if (d1 > d2) {
                return 1;
            }
            return 0;
        }
    }
}