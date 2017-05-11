/*
ID: zhm05251
LANG: JAVA
TASK: rectbarn
*/

import java.io.*;
import java.util.*;

public class rectbarn {

    private static final String INPUT_FILE_NAME = "rectbarn.in";
    private static final String OUTPUT_FILE_NAME = "rectbarn.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[][] a = new boolean[m][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            a[x][y] = true;
        }

        int s = 0;
        int[] h = new int[n];
        int[] l = new int[n];
        int[] r = new int[n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                h[j] = a[i][j] ? 0 : h[j] + 1;
            }

            int lt = 0;
            for (int j = 0; j < n; j++) {
                if (a[i][j]) {
                    lt = 0;
                    l[j] = 0;
                } else {
                    lt++;
                    if (l[j] == 0) {
                        l[j] = lt;
                    } else {
                        l[j] = l[j] < lt ? l[j] : lt;
                    }
                }
            }

            int rt = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (a[i][j]) {
                    rt = 0;
                    r[j] = 0;
                } else {
                    rt++;
                    if (r[j] == 0) {
                        r[j] = rt;
                    } else {
                        r[j] = r[j] < rt ? r[j] : rt;
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                if (h[j] * (l[j] + r[j] - 1) > s) {
                    s = h[j] * (l[j] + r[j] - 1);
                }
            }
        }

        out.println(String.valueOf(s));
        out.close();
    }
}
