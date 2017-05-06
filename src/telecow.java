/*
ID: zhm05251
LANG: JAVA
TASK: telecow
*/

import java.io.*;
import java.util.*;

public class telecow {

    private static final String INPUT_FILE_NAME = "telecow.in";
    private static final String OUTPUT_FILE_NAME = "telecow.out";
    private static final int MAX = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int t = Integer.parseInt(st.nextToken()) - 1;

        int[][] a = new int[m * 2][m * 2];
        for (int i = 0; i < m; i++) {
            a[i * 2][i * 2 + 1] = 1;
        }
        a[s * 2][s * 2 + 1] = MAX;
        a[t * 2][t * 2 + 1] = MAX;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            a[x * 2 + 1][y * 2] = MAX;
            a[y * 2 + 1][x * 2] = MAX;
        }

        int[][] g = new int[m * 2][m * 2];
        for (int i = 0; i < m * 2; i++) {
            for (int j = 0; j < m * 2; j++) {
                g[i][j] = a[i][j];
            }
        }
        int maxFlow = ek(m * 2, s * 2, t * 2 + 1, g);
        out.println(String.valueOf(maxFlow));

        boolean[] chosenSpots = new boolean[m];
        for (int k = 0; k < m; k++) {
            if (k == s || k == t) {
                continue;
            }

            g = new int[m * 2][m * 2];
            for (int i = 0; i < m * 2; i++) {
                for (int j = 0; j < m * 2; j++) {
                    g[i][j] = a[i][j];
                }
            }
            for (int i = 0; i < m; i++) {
                if (chosenSpots[i]) {
                    g[i * 2][i * 2 + 1] = 0;
                }
            }
            g[k * 2][k * 2 + 1] = 0;

            int rm = ek(m * 2, s * 2, t * 2 + 1, g);
            if (maxFlow > rm) {
                chosenSpots[k] = true;
                maxFlow = rm;
            }
        }

        boolean flag = false;
        for (int i = 0; i < m; i++) {
            if (chosenSpots[i]) {
                if (flag) {
                    out.print(" ");
                }
                flag = true;
                out.print(String.valueOf(i + 1));
            }
        }
        out.println();
        out.close();
    }

    private static final int ek(int m, int s, int t, int[][] g) {
        int result = 0;
        int[] pre = new int[m];
        int[] flow = new int[m];
        Queue<Integer> queue = new LinkedList<>();

        while (true) {
            for (int i = 0; i < m; i++) {
                pre[i] = -1;
                flow[i] = Integer.MAX_VALUE;
            }
            pre[s] = s;
            queue.clear();
            queue.add(s);

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == t) {
                    break;
                }
                for (int i = 0; i < m; i++) {
                    if (pre[i] < 0 && g[cur][i] != 0) {
                        pre[i] = cur;
                        flow[i] = flow[cur] < g[cur][i] ? flow[cur] : g[cur][i];
                        queue.add(i);
                    }
                }
            }

            if (pre[t] < 0) {
                break;
            }

            int i = t;
            while (i != s) {
                g[pre[i]][i] -= flow[t];
                g[i][pre[i]] += flow[t];
                i = pre[i];
            }

            result += flow[t];
        }
        return result;
    }

}
