/*
ID: zhm05251
LANG: JAVA
TASK: ariprog
*/

import java.io.*;
import java.util.*;

public class ariprog {
    private static final String INPUT_FILE_NAME = "ariprog.in";
    private static final String OUTPUT_FILE_NAME = "ariprog.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> mDataList = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                int x = i * i + j * j;
                if (!mDataList.contains(x)) {
                    mDataList.add(x);
                }
            }
        }
        Collections.sort(mDataList);

        List<Pair> results = new ArrayList<>();
        int size = mDataList.size();
        int lastNumber = mDataList.get(size - 1);
        int firstNumber = mDataList.get(0);
        int maxB = (lastNumber - firstNumber) / (n - 1);
        int[] flags = new int[lastNumber + 1];

        for (int i = 0; i < size; i++) {
            flags[mDataList.get(i)] = Integer.MAX_VALUE;
        }

        for (int b = 1; b <= maxB; b++) {
            for (int i = 0; i < size; i++) {
                int a = mDataList.get(i);
                if (flags[a] == b) {
                    continue;
                }
                flags[a] = b;
                if (a + b * (n - 1) > lastNumber) {
                    break;
                }
                int j = 1;
                while (a + b * j <= lastNumber) {
                    if (flags[a + b * j] <= 0) {
                        break;
                    }
                    flags[a + b * j] = b;
                    if (j >= n - 1) {
                        results.add(new Pair(a + b * (j + 1 - n), b));
                    }
                    j++;
                }
            }
        }

        Collections.sort(results);
        if (results.isEmpty()) {
            out.println("NONE");
        } else {
            for (Pair pair : results) {
                out.println(pair.getX() + " " + pair.getY());
            }
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
            if (y < o.getY()) {
                return -1;
            }
            if (y == o.getY() && x < o.getX()) {
                return -1;
            }
            return 1;
        }
    }

}
