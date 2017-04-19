/*
ID: zhm05251
LANG: JAVA
TASK: rockers
*/

import java.io.*;
import java.util.*;

public class rockers {
    private static final String INPUT_FILE_NAME = "rockers.in";
    private static final String OUTPUT_FILE_NAME = "rockers.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[][][] s = new int[m + 1][n][n];
        for (int i = 1; i <= m; i++) {
            if (i == 1) {
                for (int j = 0; j < n; j++) {
                    for (int k = j; k < n; k++) {
                        int[] u = new int[t + 1];
                        for (int l = j; l <= k; l++) {
                            for (int v = t; v > 0; v--) {
                                if (u[v] > 0 && v + a[l] <= t &&
                                        u[v + a[l]] < u[v] + 1) {
                                    u[v + a[l]] = u[v] + 1;
                                }
                            }
                            if (a[l] <= t && u[a[l]] < 1) {
                                u[a[l]] = 1;
                            }
                        }
                        for (int v = t; v > 0; v--) {
                            if (u[v] > s[i][j][k]) {
                                s[i][j][k] = u[v];
                            }
                        }
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    for (int k = j; k < n; k++) {
                        if (j == k) {
                            s[i][j][k] = s[i-1][j][k];
                        }
                        for (int l = j; l < k; l++) {
                            if (s[i][j][k] < s[i - 1][j][l] + s[1][l + 1][k]) {
                                s[i][j][k] = s[i - 1][j][l] + s[1][l + 1][k];
                            }
                        }
                    }
                }
            }
        }
        out.println(String.valueOf(s[m][0][n - 1]));
        out.close();
    }
}
