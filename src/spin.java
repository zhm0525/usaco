/*
ID: zhm05251
LANG: JAVA
TASK: spin
*/

import java.io.*;
import java.util.*;

public class spin {
    private static final String INPUT_FILE_NAME = "spin.in";
    private static final String OUTPUT_FILE_NAME = "spin.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        List<Wheel> wList = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(f.readLine());
            int speed = Integer.parseInt(st.nextToken());
            Wheel wheel = new Wheel(speed);

            int w = Integer.parseInt(st.nextToken());
            for (int j = 0; j < w; j++) {
                int start = Integer.parseInt(st.nextToken());
                int width = Integer.parseInt(st.nextToken());
                wheel.addGap(start, width);
            }

            wList.add(wheel);
        }

        int s = -1;
        for (int i = 0; i < 360; i++) {
            List<Gap> list = new ArrayList<>();
            list.add(new Gap(0, 360));

            for (int j = 0; j < 5; j++) {
                list = getMixGaps(list, wList.get(j).getGaps(i));
            }

            if (list.size() > 0) {
                s = i;
                break;
            }
        }
        out.println(s < 0 ? "none" : String.valueOf(s));
        out.close();
    }

    private static List<Gap> getMixGaps(List<Gap> list1, List<Gap> list2) {
        List<Gap> list = new ArrayList<>();

        for (Gap gap1 : list1) {
            for (Gap gap2 : list2) {
                list.addAll(getMixGap(gap1, gap2));
            }
        }

        return list;
    }

    private static List<Gap> getMixGap(Gap gap1, Gap gap2) {
        List<Interval> intervals1 = gap1.getIntervals();
        List<Interval> intervals2 = gap2.getIntervals();

        List<Gap> list = new ArrayList<>();
        for (Interval interval1 : intervals1) {
            for (Interval interval2 : intervals2) {
                Interval interval = Interval.mixInterval(interval1, interval2);
                if (interval != null) {
                    list.add(new Gap(interval.getX(), interval.getY() - interval.getX()));
                }
            }
        }
        return list;
    }

    private static class Wheel {
        private int speed;
        private List<Gap> gaps;

        public Wheel(int speed) {
            this.speed = speed;
            gaps = new ArrayList<>();
        }

        public void addGap(int start, int width) {
            gaps.add(new Gap(start, width));
        }

        public List<Gap> getGaps(int time) {
            List<Gap> list = new ArrayList<>();
            for (Gap gap : gaps) {
                list.add(new Gap((gap.getStart() + speed * time) % 360, gap.getWidth()));
            }
            return list;
        }
    }

    private static class Gap {
        private int start;
        private int width;

        public Gap(int start, int width) {
            this.start = start;
            this.width = width;
        }

        public int getStart() {
            return start;
        }

        public int getWidth() {
            return width;
        }

        public List<Interval> getIntervals() {
            List<Interval> list = new ArrayList<>();
            int x = start;
            int y = start + width;
            if (y <= 360) {
                list.add(new Interval(x, y));
            } else {
                list.add(new Interval(x, 360));
                list.add(new Interval(0, y - 360));
            }
            return list;
        }
    }

    private static class Interval {
        private int x;
        private int y;

        public Interval(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public static Interval mixInterval(Interval interval1, Interval interval2) {
            int x1 = interval1.getX();
            int y1 = interval1.getY();
            int x2 = interval2.getX();
            int y2 = interval2.getY();

            if (x1 <= x2 && x2 <= y1) {
                return new Interval(x2, y1 <= y2 ? y1 : y2);
            }

            if (x2 <= x1 && x1 <= y2) {
                return new Interval(x1, y1 <= y2 ? y1 : y2);
            }

            return null;
        }
    }
}
