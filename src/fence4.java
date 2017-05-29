/*
ID: zhm05251
LANG: JAVA
TASK: fence4
*/

import java.io.*;
import java.util.*;

public class fence4 {

    private static final String INPUT_FILE_NAME = "fence4.in";
    private static final String OUTPUT_FILE_NAME = "fence4.out";

    private static final String NOFENCE = "NOFENCE";
    private static Point observerPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int ox = Integer.parseInt(st.nextToken());
        int oy = Integer.parseInt(st.nextToken());
        observerPoint = new Point(ox, oy);

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        boolean fence = true;
        for (int i = 0; i < n; i++) {
            Point p1 = points[i];
            Point p2 = points[(i + 1) % n];
            for (int j = 0; j < i; j++) {
                Point p3 = points[j];
                Point p4 = points[j + 1];
                if (segmentsIntersect(p1, p2, p3, p4)) {
                    fence = false;
                }
                if (!fence) {
                    break;
                }
            }
            if (!fence) {
                break;
            }
        }
        if (!fence) {
            out.println(NOFENCE);
        } else {
            List<Interval> results = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // adjust the order
                Point p1;
                Point p2;
                if (i == n - 2) {
                    p1 = points[0];
                    p2 = points[n - 1];
                } else if (i == n - 1) {
                    p1 = points[n - 2];
                    p2 = points[n - 1];
                } else {
                    p1 = points[i];
                    p2 = points[i + 1];
                }

                // three points in a line
                if (direction(p1, p2, observerPoint) == 0) {
                    continue;
                }

                List<IntervalD> intervals = new ArrayList<>();
                intervals.add(new IntervalD(p1.getPointD(), p2.getPointD()));
                for (int j = 0; j < n; j++) {
                    if (i == n - 2) {
                        if (j == n - 1) {
                            continue;
                        }
                    } else if (i == n - 1) {
                        if (j == n - 2) {
                            continue;
                        }
                    } else {
                        if (j == i) {
                            continue;
                        }
                    }
                    Point pj1 = points[j];
                    Point pj2 = points[(j + 1) % n];

                    IntervalD intervalJ = new IntervalD(pj1.getPointD(), pj2.getPointD());
                    List<IntervalD> list = new ArrayList<>();
                    for (IntervalD interval : intervals) {
                        list.addAll(interval.getIntervalsObserved(intervalJ));
                    }
                    intervals.clear();
                    intervals.addAll(list);
                }
                boolean flag = false;
                for (IntervalD interval : intervals) {
                    if (interval.isValid()) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    results.add(new Interval(p1, p2));
                }
            }

            out.println(String.valueOf(results.size()));
            for (Interval interval : results) {
                out.println(interval);
            }
        }
        out.close();
    }

    private static class Point {
        private int x;
        private int y;

        private PointD mPointD;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;

            mPointD = new PointD(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public PointD getPointD() {
            return mPointD;
        }

        @Override
        public String toString() {
            return "" + this.x + " " + this.y;
        }
    }

    private static class PointD {
        private double x;
        private double y;

        public PointD(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    private static class Interval {
        private Point mPoint1;
        private Point mPoint2;

        public Interval(Point p1, Point p2) {
            mPoint1 = p1;
            mPoint2 = p2;
        }

        @Override
        public String toString() {
            return "" + mPoint1 + " " + mPoint2;
        }
    }

    private static class IntervalD {
        private PointD mPoint1;
        private PointD mPoint2;

        public IntervalD(PointD p1, PointD p2) {
            mPoint1 = p1;
            mPoint2 = p2;
        }

        public PointD getPointD1() {
            return mPoint1;
        }

        public PointD getPointD2() {
            return mPoint2;
        }

        public List<IntervalD> getIntervalsObserved(IntervalD interval) {
            List<IntervalD> list = new ArrayList<>();

            PointD p1 = interval.getPointD1();
            PointD p2 = interval.getPointD2();

            double d12o = direction(p1, p2, observerPoint.getPointD());
            double d121 = direction(p1, p2, mPoint1);
            double d122 = direction(p1, p2, mPoint2);

            if (d12o * d121 >= 0 && d12o * d122 >= 0) {
                list.add(this);
                return list;
            }

            double d10 = direction(observerPoint.getPointD(), mPoint1, mPoint2);
            double d11 = direction(observerPoint.getPointD(), mPoint1, p1);
            double d12 = direction(observerPoint.getPointD(), mPoint1, p2);

            double d20 = direction(observerPoint.getPointD(), mPoint2, mPoint1);
            double d21 = direction(observerPoint.getPointD(), mPoint2, p1);
            double d22 = direction(observerPoint.getPointD(), mPoint2, p2);

            // no cover
            if (sgn(d11) * sgn(d12) >= 0 && sgn(d11) * sgn(d10) <= 0 && sgn(d12) * sgn(d10) <= 0) {
                list.add(this);
                return list;
            }

            // no cover
            if (sgn(d21) * sgn(d22) >= 0 && sgn(d21) * sgn(d20) <= 0 && sgn(d22) * sgn(d20) <= 0) {
                list.add(this);
                return list;
            }

            // all cover
            if (sgn(d11) * sgn(d12) <= 0 && sgn(d21) * sgn(d22) <= 0) {
                return list;
            }

            // cover mPoint1
            if (sgn(d11) * sgn(d12) <= 0 && sgn(d21) * sgn(d22) >= 0) {
                PointD p;
                if (sgn(d11) == sgn(d10)) {
                    p = p1;
                } else {
                    p = p2;
                }

                PointD ip = getInterSectionPoint(observerPoint.getPointD(), p, mPoint1, mPoint2);
                list.add(new IntervalD(ip, mPoint2));
                return list;
            }

            // cover mPoint2
            if (sgn(d21) * sgn(d22) <= 0 && sgn(d11) * sgn(d12) >= 0) {
                PointD p;
                if (sgn(d21) == sgn(d20)) {
                    p = p1;
                } else {
                    p = p2;
                }

                PointD ip = getInterSectionPoint(observerPoint.getPointD(), p, mPoint1, mPoint2);
                list.add(new IntervalD(ip, mPoint1));
                return list;
            }

            // cover middle
            if (sgn(d10) == sgn(d11) && sgn(d10) == sgn(d12) &&
                    sgn(d20) == sgn(d21) && sgn(d20) == sgn(d22)) {
                PointD ip1 = getInterSectionPoint(observerPoint.getPointD(), p1, mPoint1, mPoint2);
                PointD ip2 = getInterSectionPoint(observerPoint.getPointD(), p2, mPoint1, mPoint2);

                double dp0 = direction(observerPoint.getPointD(), p1, p2);
                double dp1 = direction(observerPoint.getPointD(), p1, mPoint1);
                double dp2 = direction(observerPoint.getPointD(), p1, mPoint2);

                if (sgn(dp0) == sgn(dp1)) {
                    list.add(new IntervalD(ip1, mPoint2));
                    list.add(new IntervalD(ip2, mPoint1));
                } else if (sgn(dp0) == sgn(dp2)) {
                    list.add(new IntervalD(ip1, mPoint1));
                    list.add(new IntervalD(ip2, mPoint2));
                }
                return list;
            }

            return list;
        }

        public boolean isValid() {
            double dx = mPoint1.getX() - mPoint2.getX();
            double dy = mPoint1.getY() - mPoint2.getY();
            double d = Math.sqrt(dx * dx + dy * dy);
            return d > 0.00001d;
        }
    }

    // learn from "introduction of algorithms"
    private static boolean segmentsIntersect(Point p1, Point p2, Point p3, Point p4) {
        int d1 = direction(p3, p4, p1);
        int d2 = direction(p3, p4, p2);
        int d3 = direction(p1, p2, p3);
        int d4 = direction(p1, p2, p4);
        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
                ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
            return true;
        } else if (d1 == 0 && onSegment(p3, p4, p1)) {
            return true;
        } else if (d2 == 0 && onSegment(p3, p4, p2)) {
            return true;
        } else if (d3 == 0 && onSegment(p1, p2, p3)) {
            return true;
        } else if (d4 == 0 && onSegment(p1, p2, p4)) {
            return true;
        }
        return false;
    }

    private static int direction(Point pi, Point pj, Point pk) {
        return (pj.getX() - pi.getX()) * (pk.getY() - pi.getY()) -
                (pk.getX() - pi.getX()) * (pj.getY() - pi.getY());
    }

    private static boolean onSegment(Point pi, Point pj, Point pk) {
        if ((Math.min(pi.getX(), pj.getX()) < pk.getX()) &&
                (pk.getX() < Math.max(pi.getX(), pj.getX())) &&
                (Math.min(pi.getY(), pj.getY()) < pk.getY()) &&
                (pk.getY() < Math.max(pi.getY(), pj.getY())))
            return true;
        return false;
    }


    private static double direction(PointD pi, PointD pj, PointD pk) {
        return (pj.getX() - pi.getX()) * (pk.getY() - pi.getY()) -
                (pk.getX() - pi.getX()) * (pj.getY() - pi.getY());
    }

    private static int sgn(double x) {
        if (x < 0) {
            return -1;
        } else if (x > 0) {
            return 1;
        }
        return 0;
    }


    private static PointD getInterSectionPoint(PointD p1, PointD p2, PointD p3, PointD p4) {
        double a1 = p2.getY() - p1.getY();
        double b1 = -p2.getX() + p1.getX();
        double c1 = -p1.getX() * p2.getY() + p2.getX() * p1.getY();

        double a2 = p4.getY() - p3.getY();
        double b2 = -p4.getX() + p3.getX();
        double c2 = -p3.getX() * p4.getY() + p4.getX() * p3.getY();

        double x = (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1);
        double y = (c2 * a1 - c1 * a2) / (b1 * a2 - b2 * a1);

        return new PointD(x, y);
    }

}
