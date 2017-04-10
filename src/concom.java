/*
ID: zhm05251
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.*;

public class concom {
    private static final String INPUT_FILE_NAME = "concom.in";
    private static final String OUTPUT_FILE_NAME = "concom.out";

    private static int MAX = 100;
    private static int[][] p = new int[MAX][MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        p = new int[MAX][MAX];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            p[x - 1][y - 1] = Integer.parseInt(st.nextToken());
        }

        List<Pair> results = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            boolean[] flags = new boolean[MAX];
            int[] q = new int[MAX];
            for (int j = 0; j < MAX; j++) {
                q[j] = p[i][j];
                flags[j] = true;
            }
            flags[i] = false;
            int find;
            do {
                find = -1;
                for (int j = 0; j < MAX; j++) {
                    if (flags[j] && q[j] > 50) {
                        find = j;
                        break;
                    }
                }
                if (find != -1) {
                    flags[find] = false;
                    results.add(new Pair(i + 1, find + 1));
                    for (int j = 0; j < MAX; j++) {
                        q[j] += p[find][j];
                    }
                }
            } while (find != -1);
        }
        Collections.sort(results);
        for (Pair pair : results) {
            out.println(pair.getX() + " " + pair.getY());
        }
        out.close();
    }

    public static class Pair implements Comparable<Pair> {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Pair o) {
            if (x < o.getX()) {
                return -1;
            }
            if (x == o.getX() && y < o.getY()) {
                return -1;
            }
            return 1;
        }
    }
}
