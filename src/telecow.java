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

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int t = Integer.parseInt(st.nextToken()) - 1;

        int[][] a = new int[m][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            a[x - 1][y - 1]++;
            a[y - 1][x - 1]++;
        }

        int[][] g = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = a[i][j];
            }
        }
        int maxFlow = ek(m, s, t, g);
        out.println(String.valueOf(maxFlow));

        boolean[] chosenSpots = new boolean[m];
        for (int k = 0; k < m; k++) {
            if (k == s || k == t) {
                continue;
            }

            g = new int[m][m];
            for (int i = 0; i < m; i++) {
                if (i == k || chosenSpots[i]) {
                    continue;
                }
                for (int j = 0; j < m; j++) {
                    if (j == k || chosenSpots[j]) {
                        continue;
                    }
                    g[i][j] = a[i][j];
                }
            }

            int rm = ek(m, s, t, g);
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
