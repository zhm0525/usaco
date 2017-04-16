/*
ID: zhm05251
LANG: JAVA
TASK: butter
*/

import java.io.*;
import java.util.*;

public class butter {

    private static final String INPUT_FILE_NAME = "butter.in";
    private static final String OUTPUT_FILE_NAME = "butter.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] a = new int[p];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            a[x - 1]++;
        }

        List<Path> pathList = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pathList.add(new Path(x - 1, y - 1, d));
            pathList.add(new Path(y - 1, x - 1, d));
        }

        int s = Integer.MAX_VALUE / 2;
        for (int i = 0; i < p; i++) {
            int[] d = new int[p];
            for (int j = 0; j < p; j++) {
                d[j] = i == j ? 0 : Integer.MAX_VALUE / 2;
            }

            for (int j = 0; j < p - 1; j++) {
                boolean flag = true;
                for (Path path : pathList) {
                    int start = path.getStart();
                    int end = path.getEnd();
                    int distance = path.getDistance();
                    if (d[start] + distance < d[end]) {
                        d[end] = d[start] + distance;
                        flag = false;
                    }
                }
                if (flag) {
                    break;
                }
            }

            int t = 0;
            for (int j = 0; j < p; j++) {
                t += a[j] * d[j];
            }

            if (t < s) {
                s = t;
            }
        }

        out.println(s);
        out.close();
    }

    private static class Path {
        private int start;
        private int end;
        private int distance;

        public Path(int s, int e, int d) {
            start = s;
            end = e;
            distance = d;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getDistance() {
            return distance;
        }
    }
}
