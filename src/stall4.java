/*
ID: zhm05251
LANG: JAVA
TASK: stall4
*/

import java.io.*;
import java.util.*;

public class stall4 {

    private static final String INPUT_FILE_NAME = "stall4.in";
    private static final String OUTPUT_FILE_NAME = "stall4.out";

    private static int n, m;
    private static int[][] a;
    private static boolean[] v;
    private static int[] link;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int count = Integer.parseInt(st.nextToken());
            a[i] = new int[count];
            for (int j = 0; j < count; j++) {
                a[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        link = new int[m];
        for (int i = 0; i < m; i++) {
            link[i] = -1;
        }
        v = new boolean[m];

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                v[j] = false;
            }
            if (find(i)) {
                result++;
            }
        }
        out.println(String.valueOf(result));
        out.close();
    }

    private static boolean find(int cur) {
        for (int i = 0; i < a[cur].length; i++) {
            int x = a[cur][i];
            if (!v[x]) {
                v[x] = true;
                if (link[x] < 0 || find(link[x])) {
                    link[x] = cur;
                    return true;
                }
            }
        }
        return false;
    }
}
