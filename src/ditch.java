/*
ID: zhm05251
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.*;

public class ditch {

    private static final String INPUT_FILE_NAME = "ditch.in";
    private static final String OUTPUT_FILE_NAME = "ditch.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] g = new int[m][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            g[x - 1][y - 1] += z;
        }

        int s = 0;
        int t = m - 1;
        int result = 0;

        int[] pre = new int[m];
        int[] flow = new int[m];
        Queue<Integer> queue = new LinkedList<>();

        while (true) {
            for (int i = 0; i < m; i++) {
                pre[i] = -1;
            }
            pre[s] = s;
            queue.clear();
            queue.add(s);
            flow[s] = Integer.MAX_VALUE;

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

        out.println(String.valueOf(result));
        out.close();
    }
}
