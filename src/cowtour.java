/*
ID: zhm05251
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.util.*;

public class cowtour {

    private static final String INPUT_FILE_NAME = "cowtour.in";
    private static final String OUTPUT_FILE_NAME = "cowtour.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            positions.add(new Position(x, y));
        }

        boolean[][] map = new boolean[n][n];
        double[][] dis = new double[n][n];
        int[] head = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = i == j ? true : false;
                dis[i][j] = i == j ? 0 : Double.MAX_VALUE / 2;
            }
            head[i] = i;
        }
        for (int i = 0; i < n; i++) {
            String input = f.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) == '1';
                if (map[i][j]) {
                    // count the distance
                    Position pi = positions.get(i);
                    Position pj = positions.get(j);
                    int dX = pi.getX() - pj.getX();
                    int dY = pi.getY() - pj.getY();
                    dis[i][j] = Math.sqrt(dX * dX + dY * dY);
                    // count the head
                    if (head[i] != head[j]) {
                        int hi = head[i];
                        while (hi != head[hi]) {
                            hi = head[hi];
                        }
                        head[i] = hi;

                        int hj = head[j];
                        while (hj != head[hj]) {
                            hj = head[hj];
                        }
                        head[j] = hj;

                        head[head[j]] = head[i];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int h = head[i];
            while (h != head[h]) {
                h = head[h];
            }
            head[i] = h;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dis[i][k] + dis[k][j] < dis[i][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                    }
                }
            }
        }

        double[] maxd = new double[n];
        for (int i = 0; i < n; i++) {
            double s = 0.0;
            for (int j = 0; j < n; j++) {
                if (head[i] == head[j]) {
                    if (dis[i][j] > s) {
                        s = dis[i][j];
                    }
                }
            }
            maxd[i] = s;
        }

        double s = Double.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (head[i] != head[j]) {
                    Position pi = positions.get(i);
                    Position pj = positions.get(j);
                    int dX = pi.getX() - pj.getX();
                    int dY = pi.getY() - pj.getY();
                    double d = Math.sqrt(dX * dX + dY * dY);

                    double sr = maxd[i] + maxd[j] + d;
                    s = sr < s ? sr : s;
                }
            }
        }

        // waste nearly a full night time for this!!!
        for (int i = 0; i < n; i++) {
            if (maxd[i] > s) {
                s = maxd[i];
            }
        }
        out.println(String.format("%.6f", s));
        out.close();
    }

    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
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
