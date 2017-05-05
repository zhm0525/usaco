/*
ID: zhm05251
LANG: JAVA
TASK: picture
*/

import java.io.*;
import java.util.*;

public class picture {

    private static final String INPUT_FILE_NAME = "picture.in";
    private static final String OUTPUT_FILE_NAME = "picture.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Interval> xList = new ArrayList<>();
        List<Interval> yList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            xList.add(new Interval(x1, x2, y1, true));
            xList.add(new Interval(x1, x2, y2, false));

            yList.add(new Interval(y1, y2, x1, true));
            yList.add(new Interval(y1, y2, x2, false));
        }

        int result = 0;

        Collections.sort(xList);
        IntervalTree xTree = new IntervalTree(-10000, 10000);
        for (Interval interval : xList) {
            result += xTree.update(interval);
        }

        Collections.sort(yList);
        IntervalTree yTree = new IntervalTree(-10000, 10000);
        for (Interval interval : yList) {
            result += yTree.update(interval);
        }

        out.println(String.valueOf(result));
        out.close();
    }

    private static class Interval implements Comparable<Interval> {
        private int x, y, level;
        private boolean isStart;

        public Interval(int x, int y, int level, boolean isStart) {
            this.x = x;
            this.y = y;
            this.level = level;
            this.isStart = isStart;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getLevel() {
            return level;
        }

        public boolean isStart() {
            return isStart;
        }

        @Override
        public int compareTo(Interval interval) {
            if (level < interval.getLevel()) {
                return -1;
            } else if (level > interval.getLevel()) {
                return 1;
            }
            if (isStart && !interval.isStart()) {
                return -1;
            } else if (!isStart && interval.isStart()) {
                return 1;
            }
            return 0;
        }
    }

    private static class IntervalTree {
        private int x, y, thick;
        private IntervalTree mLeft, mRight;

        public IntervalTree(int x, int y) {
            this(x, y, 0);
        }

        public IntervalTree(int x, int y, int thick) {
            this.x = x;
            this.y = y;
            this.thick = thick;
            mLeft = null;
            mRight = null;
        }

        public int update(Interval interval) {
            return update(interval.getX(), interval.getY(), interval.isStart());
        }

        public int update(int x, int y, boolean flag) {
            // this.x <= x <= y <= this.y
            if (x < this.x || this.y < y || x >= y) {
                // error
                return 0;
            }

            if (x == this.x && y == this.y &&
                    mLeft == null && mRight == null) {
                if (flag) {
                    int count = thick == 0 ? y - x : 0;
                    thick++;
                    return count;
                } else {
                    thick--;
                    return thick == 0 ? y - x : 0;
                }
            }
            int m = (this.x + this.y) / 2;
            if (y <= m) {
                int count = getLeftTree(this.x, m).update(x, y, flag);
                if (thick > 0) {
                    getRightTree(m, this.y);
                    thick = 0;
                }
                return count;
            } else if (x >= m) {
                int count = getRightTree(m, this.y).update(x, y, flag);
                if (thick > 0) {
                    getLeftTree(this.x, m);
                    thick = 0;
                }
                return count;
            } else {
                int leftCount = getLeftTree(this.x, m).update(x, m, flag);
                int rightCount = getRightTree(m, this.y).update(m, y, flag);
                thick = 0;
                return leftCount + rightCount;
            }
        }

        private IntervalTree getLeftTree(int x, int y) {
            if (mLeft == null) {
                mLeft = new IntervalTree(x, y, thick);
            }
            return mLeft;
        }

        private IntervalTree getRightTree(int x, int y) {
            if (mRight == null) {
                mRight = new IntervalTree(x, y, thick);
            }
            return mRight;
        }

    }
}
