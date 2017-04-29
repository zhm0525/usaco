/*
ID: zhm05251
LANG: JAVA
TASK: bigbrn
*/

import java.io.*;
import java.util.*;

public class bigbrn {

    private static final String INPUT_FILE_NAME = "bigbrn.in";
    private static final String OUTPUT_FILE_NAME = "bigbrn.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] a = new int[n + 1][n + 1];


        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            a[x][y] = 1;
        }

        int[][] w = new int[n + 1][n + 1];
        int[][] h = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                w[i][j] = w[i][j - 1] + a[i][j];
                h[i][j] = h[i - 1][j] + a[i][j];
            }
        }

        int[][] s = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == 0) {
                    int t = s[i - 1][j - 1];
                    while (t > 0) {
                        if (w[i][j] - w[i][j - t - 1] == 0 &&
                                h[i][j] - h[i - t - 1][j] == 0) {
                            break;
                        }
                        t--;
                    }
                    s[i][j] = t + 1;
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s[i][j] > result) {
                    result = s[i][j];
                }
            }
        }
        out.println(String.valueOf(result));
        out.close();
    }
}
